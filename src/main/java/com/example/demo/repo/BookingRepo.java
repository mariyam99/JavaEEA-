package com.example.demo.repo;


import com.example.demo.enitity.Booking;
import com.example.demo.enitity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {

    Booking findByBookingId(Long bookingId);

}
