package com.example.demo.mobileController;

import com.example.demo.enitity.Booking;
import com.example.demo.enitity.User;
import com.example.demo.repo.BookingRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/mobile")
public class BookingMobileController {

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    UserRepo userRepo;

    @PostMapping("/getBooking")
    public List<Booking> getBookingList()
    {
        List<Booking> bookingList = bookingRepo.findAll();
        return bookingList;
    }

    @PostMapping("removeBooking/{id}")
    public HashMap<String,String> removeBook(@PathVariable(value = "id")Long id)
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

    @PostMapping("getUserBook/{userName}")
    public List<Booking> getUserBookingList( HttpServletRequest request,Booking booking)
    {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userName");


            User user = userRepo.findByUserName(username);
            booking.setUser(user);

            List<Booking> booking1 = bookingRepo.findAll();
            return booking1;


    }






}
