package com.example.demo.repo;

import com.example.demo.enitity.Hotel;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel,Long> {

boolean existsByHotelName(String hotelName);


}
