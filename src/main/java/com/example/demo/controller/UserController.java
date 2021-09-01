package com.example.demo.controller;

import com.example.demo.enitity.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/")
    public String index(){
        return "homepage";
    }

    @RequestMapping("/registration")
    public String registerPage(){
        return "registration";
    }

    @RequestMapping("/backlogin")
    public String back(){
        return "homepage";
    }


    @Transactional
    @RequestMapping(value = "/process-Register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute(value = "user")User user,
                                  BindingResult result ,Model model ){
        if (!userRepo.existsByUserName(user.getUserName()))
        {
            if(!userRepo.existsByEmail(user.getEmail()))
            {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encodedPassword);

                user.setRole("CUSTOMER");

                userRepo.save(user);
                model.addAttribute("registarionSucess","Sucessfully Registered!");
                return "homepage";
            }
            else {
                model.addAttribute("failure","Email already exists");
                return "registration";
            }
        }
        else {
            model.addAttribute("failure","Username already exists");
            return "registration";
        }
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String processLogin(@Valid @ModelAttribute(value = "user") User user, Model model, HttpServletRequest request ) {

            if (!userRepo.existsByUserName(user.getUserName())) {

                model.addAttribute("userNameError", "Username has not been found");
                return "homepage";
            } else {
                User userRole = userRepo.findByUserName(user.getUserName());
                if ((userRole.validatePassword(user.getPassword(), userRole.getPassword())) && (userRole.getUserName().equals(user.getUserName()))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("userName", userRole.getUserName());
                    session.setMaxInactiveInterval(60 * 60);

                    model.addAttribute("user", "Welcome");


                    if (userRole.getRole().equals("ADMIN")) {
                        return "adminHome";

                    } else if (userRole.getRole().equals("MANAGER")) {
                        return "managerHome";
                    }
                    else{

                        return "customerHome";
                    }

                } else {
                    model.addAttribute("passwordIncorrect", "Entered Wrong Password");
                    return "homepage";
                }
            }

    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return "homepage";

    }

    @RequestMapping("/home")
    public String customerHomepage()
    {
        return "customerHome";
    }


    @RequestMapping("/updateProfile")
    public String updateProfile()
    {


        return "updateProfile";
    }



    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    public String updateDeatils(User user, HttpServletRequest request,Model model,
                                @RequestParam(name = "oldPassword",required = false) String oldPassword,
                                @RequestParam(name = "newPassword",required = false)String newPassword)
    {


        if(user.getEmail()=="")
        {
            user.setEmail(null);
        }

        if(user.getTel()=="")
        {
            user.setTel(null);
        }


        if (oldPassword=="" && newPassword=="")
        {
            oldPassword = null;
            newPassword= null;
        }

        HttpSession session =request.getSession();
        String username=(String)session.getAttribute("userName");

        if(userRepo.existsByUserName(username))
        {
            User user1=userRepo.findByUserName(username);
            model.addAttribute("username",user1);

            if(user.getEmail()!= null)
            {
                user1.setEmail(user.getEmail());
            }

            if(user.getTel() != null)
            {
                user1.setTel(user.getTel());
            }

            if(oldPassword != null && newPassword != null)
            {
                if((user.validatePassword(oldPassword,user.getPassword())) && (user.getUserName().equals(username)))
                {
                    String encryptedPassword = new BCryptPasswordEncoder().encode(newPassword);
                    user1.setPassword(encryptedPassword);
                    model.addAttribute("sucessPassword","Password was changed");
                }
                else
                {
                    model.addAttribute("invalid","entered old password");
                    return "redirect:/updateProfile";
                }

            }
            userRepo.updateUserProfile(user1);
            model.addAttribute("sucess","Profile update sucefully");
            return "redirect:/updateProfile";


        }
        else
        {
            model.addAttribute("invalid","user not found.");
            return "redirect:/updateProfile";
        }

    }

    @RequestMapping("/updateManagerProfile")
    public String updateManagerProfile()
    {
        return "updateManagerProfile";
    }







}
