package com.example.demo.mobileController;

import com.example.demo.enitity.Booking;
import com.example.demo.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/mobile")
public class BookingMobileController {

    @Autowired
    BookingRepo bookingRepo;

    @PostMapping("/getBooking")
    public List<Booking> getBookingList()
    {
        List<Booking> bookingList = bookingRepo.findAll();
        return bookingList;
    }

    @PostMapping("removeBooking/{id}")
    public HashMap<String,String> removeHotel(@PathVariable(value = "id")Long id)
    {
        HashMap<String,String> hashMap = new HashMap<>();

        Booking booking= bookingRepo.findByBookingId(id);
        if (bookingRepo.existsById(booking.getBookingId()))
        {
            bookingRepo.deleteById(id);
            hashMap.put("result","success");
        }else
        {
            hashMap.put("result","failure");
        }
        return hashMap;
    }





}
