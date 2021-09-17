package com.example.demo.mobileController;


import com.example.demo.enitity.Employees;
import com.example.demo.enitity.Hotel;
import com.example.demo.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mobile")
public class EmployeeMobileController {

    @Autowired
    EmployeeRepo employeeRepo;


    @PostMapping("/getEmployee")
    public List<Employees> getHotelList()
    {
        List<Employees> employeesList = employeeRepo.findAll();
        return employeesList;
    }



}
