package com.example.demo.mobileController;


import com.example.demo.enitity.Hotel;
import com.example.demo.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/mobile")
public class HotelMobileController {

    @Autowired
    HotelRepo hotelRepo;


    @PostMapping("/gethotel")
    public List<Hotel> getHotelList()
    {
        List<Hotel> hotellist = hotelRepo.findAll();
        return hotellist;
    }



    @PostMapping("/registerHotel")
    public HashMap<String,String> registerHotel(@RequestBody Hotel hotel)
    {
        HashMap<String,String> message = new HashMap<>();

        if(!hotelRepo.existsByHotelName(hotel.getHotelName()))
        {
            Hotel hotel1= hotelRepo.save(hotel);
            message.put("result","Success");
            message.put("hotel",hotel1.getHotelName());
        }else{
            message.put("result","Failure");
        }
        return message;

    }


    @PostMapping("removeHotel/{id}")
    public HashMap<String,String> removeHotel(@PathVariable(value = "id")Long id)
    {
        HashMap<String,String> hashMap = new HashMap<>();

        Hotel hotel = hotelRepo.findByHotelID(id);
        if(hotelRepo.existsByHotelName(hotel.getHotelName()))
        {
            hotelRepo.deleteById(id);
            hashMap.put("result","success");
        }else
        {
            hashMap.put("result","failure");
        }
        return hashMap;

    }





}
