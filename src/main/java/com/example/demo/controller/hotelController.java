package com.example.demo.controller;

import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.User;
import com.example.demo.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class hotelController {

    @Autowired
    private HotelRepo hotelrepo;

    @RequestMapping("/hotelform")
    public String addHotelPage(){
        return "hotelform";

    }

    @RequestMapping("/adminHome")
    public String employee()
    {
        return "adminHome";
    }


    @Transactional
    @RequestMapping(value = "/addHotel", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute(value = "hotel") Hotel hotel,
                                  BindingResult result , Model model ) {

        if(!hotelrepo.existsByHotelName(hotel.getHotelName()))
        {
            hotelrepo.save(hotel);
            model.addAttribute("sucessHotel","Hotel sucessfully added");
            return "hotelform";
        }
        else{
            model.addAttribute("failure","Hotel Already exists");
            return "hotelform";
        }

    }

    @RequestMapping("/viewHotel")
    public String viewHotel()
    {
        return "viewHotel";
    }






}
