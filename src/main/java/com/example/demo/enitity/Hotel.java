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
@Table(name="hotel")
public class Hotel {

    @Id
    @Column(name = "hotelid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotelID;
    @Column(nullable = false,unique = true, name= "hotel_name")
    private String hotelName;
    @Column(nullable = false,name= "hotel_city")
    private String hotelCity;
    @Column(nullable = false,name= "hotel_phone_number")
    private String hotelPhoneNumber;
    @Column(nullable = false,name= "hotel_address")
    private String hotelAddress;

}
