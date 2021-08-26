package com.example.demo.controller;

import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.User;
import com.example.demo.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String listhotels(Model model){
        model.addAttribute("hotellist", hotelrepo.findAll());
        return "viewHotel";

    }

    @Transactional
    @RequestMapping(value = "/deleteHotel",method = RequestMethod.POST)
    public String deleteHotel(Hotel hotel,Model model)
    {

        Hotel hotel1= hotelrepo.getById(hotel.getHotelID());
        try {
        hotelrepo.deleteById(hotel1.getHotelID());
            }
        catch (Exception e) {
            model.addAttribute("exception","Something has gone wrong");
        }
        model.addAttribute("successDeleteHotel", hotel1.getHotelName() + "\t removed from the list");
        return "redirect:/viewHotel";
    }







}
