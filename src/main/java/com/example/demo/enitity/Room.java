package com.example.demo.enitity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long roomId;
    @Column(nullable = false)
    public Integer roomNumber;
    @Column(nullable = false)
    public Integer roomFloor;
    @Column(nullable = false)
    public String status;
    @Column(nullable = false)
    public String roomDetails;
    @Column(nullable = false)
    public double priceOfRoom;
    @Column(nullable = false)
    public String roomType;





}
