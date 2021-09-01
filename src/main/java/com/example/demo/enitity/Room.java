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
    @Column(name = "room_id",unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;
    @Column(nullable = false)
    private Integer roomNumber;
    @Column(nullable = false)
    private Integer roomFloor;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String roomDetails;
    @Column(nullable = false)
    private double priceOfRoom;
    @Column(nullable = false)
    private String roomType;
    @ManyToOne
    @JoinColumn(name="hotelid")
    private Hotel hotel;




}
