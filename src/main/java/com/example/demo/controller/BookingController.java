package com.example.demo.controller;

import com.example.demo.repo.BookingRepo;
import com.example.demo.repo.HotelRepo;
import com.example.demo.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private RoomRepo roomRepo;



}
