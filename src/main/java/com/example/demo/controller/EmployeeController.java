package com.example.demo.controller;

import com.example.demo.enitity.Employees;
import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.User;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private HotelRepo hotelRepo;


    @RequestMapping("/employee")
    public String registerPage(){
        return "employee";
    }

    @Transactional
    @RequestMapping(value = "/addEmployee", method= RequestMethod.POST)
    public String addEmployee(@Valid @ModelAttribute(value = "employee") Employees employees, Model model)
    {

        List<Hotel> hotelStringList= hotelRepo.findHotelByHotelName();
        model.addAttribute("hotelList",hotelStringList);

        if(!employeeRepo.existsBynic(employees.getNic()))
        {
            employeeRepo.save(employees);
            model.addAttribute("Sucessfully","Sucessfully Regsitered!");
            return "employee";
        }
        else{
            model.addAttribute("failure","Already existing employee!");
            return "employee";

        }

    }



}
