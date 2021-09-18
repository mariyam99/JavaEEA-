package com.example.demo.repo;

import com.example.demo.enitity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface HotelRepo extends JpaRepository<Hotel,Long> {

boolean existsByHotelName(String hotelName);

boolean existsByHotelPhoneNumber(String hotelPhoneNumber);

Hotel findByHotelID(Long hotelID);

@Transactional
@Modifying
@Query("UPDATE Hotel hotel SET hotel.hotelPhoneNumber=:hotelPhoneNumber where hotel.hotelID=:id")
int updateHotel(String hotelPhoneNumber,Long id );



}
