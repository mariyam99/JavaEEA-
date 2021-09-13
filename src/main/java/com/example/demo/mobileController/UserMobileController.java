package com.example.demo.mobileController;

import com.example.demo.enitity.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;


@RestController
@RequestMapping("/api/mobile")
public class UserMobileController {

    @Autowired
    UserRepo userRepo;

    @PostMapping("/signIn")
    public HashMap<String,String> login(@RequestBody User user)
    {
    HashMap<String,String> message = new HashMap<>();

    try {
        if (userRepo.existsByUserName(user.getUserName())) {
            User user1 = userRepo.findByUserName(user.getUserName());

            if ((user1.validatePassword(user.getPassword(), user1.getPassword())) && (user1.getUserName().equals(user.getUserName())))
            {
                message.put("result", "Success");
                message.put("user", user1.getRole());
                message.put("user1", user1.getUserName());

            }else
            {
                message.put("result", "Failure");
                return message;
            }

        } else {
            message.put("result", "Failure");
            return message;
        }
    }
    catch (Exception e)
    {
        message.put("result","Failure");
        return message;
    }
    return message;
    }

    @PostMapping("/signUp")
    public HashMap<String,String> register(@RequestBody User user) throws ParseException {
        HashMap<String,String> message = new HashMap<>();

        if(!userRepo.existsByUserName(user.getUserName()))
        {
            if(!userRepo.existsByEmail(user.getEmail()))
            {

                user.setRole("CUSTOMER");
                User user1=  userRepo.save(user);
                message.put("result","Success");
                message.put("user",user1.getUserName());
            }
            else
            {
                message.put("result","Email");

            }

        }else
        {
            message.put("result","Username");
        }
        return  message;


    }


}
