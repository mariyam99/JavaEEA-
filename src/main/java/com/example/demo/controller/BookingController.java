package com.example.demo.controller;

import com.example.demo.enitity.Booking;
import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.Room;
import com.example.demo.repo.BookingRepo;
import com.example.demo.repo.HotelRepo;
import com.example.demo.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private RoomRepo roomRepo;

    @RequestMapping(value = "/searchRoom", method = RequestMethod.POST)
    public String addBooking(Model model, Booking booking, Room room,
                             @RequestParam(name="startDate",required = false) String startDate,
                             @RequestParam(name = "endDate",required = false) String endDate) throws ParseException {


       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date start = dateFormat.parse(startDate);
        Date end = dateFormat.parse(endDate);

            if(start.before(end))
            {
                List<Room> room1= roomRepo.findRoom(room);

                model.addAttribute("rooms",room1);
                model.addAttribute("avaible","The avaiable rooms");
                return "availableRooms";

            }else
            {
                model.addAttribute("novalid","The rooms are not aviabale");
                return "redirect:/customerHome";
            }

    }






}
