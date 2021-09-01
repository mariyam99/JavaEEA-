package com.example.demo.controller;


import com.example.demo.enitity.Employees;
import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.Room;
import com.example.demo.repo.HotelRepo;
import com.example.demo.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoomController {


    @Autowired
    private RoomRepo roomRepo;


    @Autowired
    private HotelRepo hotelRepo;


    @RequestMapping("/addRoomform")
    public String addHotelPage(Model model){

        List<Hotel> hotelStringList= hotelRepo.findAll();
        model.addAttribute("hotels",hotelStringList);
        return "addRoom";
    }

    @RequestMapping(value = "/addRoom" , method = RequestMethod.POST)
    public String addRoom(Room room, Model model,
                          @RequestParam(name="id") Long id)
    {
        List<Hotel> hotelList =hotelRepo.findAll();
        model.addAttribute("hotels",hotelList);

        Hotel hotel=hotelRepo.findByHotelID(id);
        room.setHotel(hotel);

        if(!roomRepo.existsByRoomId(room.getRoomId()))
        {

            if(!roomRepo.existsByRoomNumberAndRoomFloorAndHotel(room.getRoomNumber(), room.getRoomFloor(), room.getHotel())) {

                roomRepo.save(room);
                model.addAttribute("Sucess", "Room is sucessfully added");
            }
            else
            {
                model.addAttribute("failure","Already existing Room!");
            }
            }
        else
        {
            model.addAttribute("failure","Already existing Room!");

        }
        return "addRoom";



    }

    @RequestMapping("/viewRoom")
    public String listrooms(Model model){

        model.addAttribute("roomList", roomRepo.findAll());
        return "viewRoom";

    }


    @Transactional
    @RequestMapping(value = "/deleteRoom",method = RequestMethod.POST)
    public String deleteRoom(Room room, Model model)
    {
        Room room1= roomRepo.getById(room.getRoomId());
        roomRepo.deleteById(room1.getRoomId());

        model.addAttribute("sucessDeleteRoom",room1.getRoomNumber()+ "\t has been removed from " +room1.getHotel()+"\t removed from list");
        return "redirect:/viewRoom";

    }

    @RequestMapping(value = "/updateRoom",method = RequestMethod.POST)
    public  String updateRoomStatus(Room room,Model model)
    {
         Room room1=roomRepo.getById(room.getRoomId());

         if(room.getRoomId() != null)
         {
            roomRepo.updateRoom(room1.getStatus(),room1.getRoomId());
            room1.setStatus(room.getStatus());
            model.addAttribute("sucessRoom","Updateed sucessfully the room status");
            return "redirect:/viewRoom";
         }
        else{
            model.addAttribute("invalid","Room not found");
             return "redirect:/viewRoom";
         }


    }



}
