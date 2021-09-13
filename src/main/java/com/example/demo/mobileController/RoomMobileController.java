package com.example.demo.mobileController;


import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.Room;
import com.example.demo.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mobile")
public class RoomMobileController {

    @Autowired
    RoomRepo roomRepo;

    @PostMapping("/getRoom")
    public List<Room> getHotelList()
    {
        List<Room> roomList = roomRepo.findAll();
        return roomList;
    }



}
