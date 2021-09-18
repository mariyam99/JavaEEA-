package com.example.demo.repo;


import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {

    boolean existsByRoomId(Long roomId);


    boolean existsByRoomNumberAndRoomFloorAndHotel(Integer roomNumber, Integer roomFloor, Hotel hotel);

    @Transactional
    @Modifying
    @Query("UPDATE Room room SET room.status=:status where room.roomId=:id")
    int updateRoom(String status,Long id);


    @Query(value = "SELECT * FROM room WHERE status LIKE 'AVAILABLE'",nativeQuery = true)
    List<Room> findRoom(Room room);

    Room findByRoomId(Long roomId);

    @Query(value = "SELECT * FROM room WHERE status LIKE 'AVAILABLE'",nativeQuery = true)
    List<Room> findRoomsA();



}
