package com.example.demo.repo;


import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {

    boolean existsByRoomId(Long roomId);


    boolean existsByRoomNumberAndRoomFloorAndHotel(Integer roomNumber, Integer roomFloor, Hotel hotel);


}