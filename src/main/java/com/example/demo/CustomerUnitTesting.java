package com.example.demo;


import com.example.demo.enitity.*;
import com.example.demo.repo.BookingRepo;
import com.example.demo.repo.RoomRepo;
import com.example.demo.repo.UserRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerUnitTesting {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Test
    public void getUserlist()
    {
        List<User> userList = userRepo.findAll();
        Assert.assertNotNull(userList);
    }


    @Test
    public void getBooklist()
    {
        List<Booking> bookingList = bookingRepo.findAll();
        Assert.assertNotNull(bookingList);
    }

    @Test
    public void getRegisterUser()
    {
        Date date= new Date();
     User user= new User();
     user.setRole("CUSTOMER");
     user.setUserName("ahmed");
     user.setDateOfBirth(date);
     user.setPassword("ahmed123");
     user.setTel("0777857626");
     user.setEmail("wwwahamed@gmail.com");
     userRepo.save(user);
     Assert.assertNotNull(user);
    }

    @Test
    public void getReservation()
    {
        Date date= new Date();
        Booking booking= new Booking();
        booking.setStartDate(date);
        booking.setEndDate(date);
        booking.setSpecialRequest("");
        booking.setUser(userRepo.findById(33L).get());
        booking.setRoom(roomRepo.getById(37L));
        bookingRepo.save(booking);
        Assert.assertNotNull(booking);
    }

    @Test
    public void getOneUser()
    {
        User user=  userRepo.findById(25L).get();
        Assert.assertEquals("mariyam@gmail.com",user.getEmail());
    }


    @Test
    public void getOneBooking()
    {
        Booking booking=  bookingRepo.findById(2L).get();
        Assert.assertEquals(33,booking.getUser().getId().getClass());
    }

    @Test
    public void getDeleteBooking()
    {
        Booking booking = bookingRepo.getById(2L);
        bookingRepo.deleteById(booking.getBookingId());
        Assert.assertNotNull(booking);
    }


    @Test
    public void getDeleteUser()
    {
        User user = userRepo.getById(25L);
        userRepo.deleteById(user.getId());
        Assert.assertNotNull(user);
    }


    @Test
    public void getUserUpdate()
    {
        User user = userRepo.findById(33L).get();
        user.setUserName("Nabila");
        userRepo.save(user);
        Assert.assertNotEquals("ahmed",userRepo.findById(33L).get().getUserName());
    }

    @Test
    public void getBookingUpdate()
    {

        Booking booking = bookingRepo.findById(2L).get();
        bookingRepo.save(booking);
        Assert.assertEquals("2021-09-05 00:00:00.0",bookingRepo.findById(2L).get().getStartDate());

    }


    @Test
    public void getUserUpdatePassword()
    {
        User user = userRepo.findById(33L).get();
        user.setPassword("Nabila");
        userRepo.save(user);
        Assert.assertNotEquals("ahmed",userRepo.findById(33L).get().getPassword());
    }




}
