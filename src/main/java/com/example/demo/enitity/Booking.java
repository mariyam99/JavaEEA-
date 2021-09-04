package com.example.demo.enitity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "booking_Id",unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long bookingId;
    @Column(nullable = false)
    public String specialRequest;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    public Date startDate;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    public Date endDate;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    public Booking(Long bookingId, Date startDate, Date endDate) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
