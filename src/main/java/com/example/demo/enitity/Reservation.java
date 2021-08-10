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
@Table(name = "user")
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long reservationId;
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
