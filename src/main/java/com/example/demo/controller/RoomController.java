package com.example.demo.controller;


import com.example.demo.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoomController {


    @Autowired
    private RoomRepo roomRepo;


    @RequestMapping("/addRoomform")
    public String addHotelPage(){
        return "addRoom";
    }












}
