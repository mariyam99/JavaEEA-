package com.example.demo.mobileController;


import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.Room;
import com.example.demo.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/mobile")
public class RoomMobileController {

    @Autowired
    RoomRepo roomRepo;

    @PostMapping("/getRoom")
    public List<Room> getRoomList()
    {
        List<Room> roomList = roomRepo.findAll();
        return roomList;
    }


    @PostMapping("removeRoom/{rid}")
    public HashMap<String,String> removeRoom(@PathVariable(value = "rid")Long rid)
    {
        HashMap<String,String> hashMap = new HashMap<>();

        Room room = roomRepo.findByRoomId(rid);
        if(roomRepo.existsByRoomId(room.getRoomId()))
        {
            roomRepo.deleteById(rid);
            hashMap.put("result","success");
        }else
        {
            hashMap.put("result","failure");
        }
        return hashMap;

    }


    @PostMapping("/getSingleRoom")
    public List<Room> getSingleRoomList()
    {
        List<Room> roomList =roomRepo.findRoomsA();
        return roomList;
    }






}
