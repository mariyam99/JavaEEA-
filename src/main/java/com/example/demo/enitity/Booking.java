package com.example.demo.enitity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "booking")
public class Booking {

    @Id
    @Column(name = "booking_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long bookingId;
    @Column(nullable = false)
    public String specialRequest;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    public Date startDate;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    public Date endDate;
    @Column(nullable = false)
    public Integer noOfGuests;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;




}
