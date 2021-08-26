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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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




}
