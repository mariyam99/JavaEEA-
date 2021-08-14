package com.example.demo.repo;

import com.example.demo.enitity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepo extends JpaRepository<Hotel,Long> {

boolean existsByHotelName(String hotelName);


}
