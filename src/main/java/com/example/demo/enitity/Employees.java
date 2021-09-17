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
@Table(name = "employee")
public class Employees {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long employeeId;
    @Column(nullable = false,name="first_name")
    public String firstName;
    @Column(nullable = false,name="last_name")
    public String lastName;
    @Column(nullable = false)
    public String address;
    @Column(nullable = false,name="phone_number")
    public String phoneNumber;
    @Column(nullable = false)
    public double salary;
    @Column(nullable = false,name = "employee_role")
    public String employeeRole;
    @Column(nullable = false,unique = true)
    public String nic;
    @Column(nullable = false,name = "email_employee")
    public String emailEmployee;
    @ManyToOne
    @JoinColumn(name="hotelid")
    private Hotel hotel;

    public Employees(String firstName, String lastName, String address, String phoneNumber, double salary, String employeeRole, String nic, String emailEmployee, Hotel hotel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.employeeRole = employeeRole;
        this.nic = nic;
        this.emailEmployee = emailEmployee;
        this.hotel = hotel;
    }
}
