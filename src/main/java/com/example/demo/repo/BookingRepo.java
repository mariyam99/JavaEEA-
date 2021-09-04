package com.example.demo.repo;


import com.example.demo.enitity.Booking;
import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {



}
