package com.example.demo.repo;


import com.example.demo.enitity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {



}
