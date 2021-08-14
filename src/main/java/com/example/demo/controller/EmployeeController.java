package com.example.demo.controller;

import com.example.demo.emailSenderService;
import com.example.demo.enitity.Employees;
import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.User;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.HotelRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private HotelRepo hotelRepo;




    @RequestMapping("/employee")
    public String registerPage(Model model){

        List<Hotel> hotelStringList= hotelRepo.findAll();
        model.addAttribute("hotels",hotelStringList);
        model.addAttribute("new Employee",new Employees());

        return "employee";
    }

    @Transactional
    @RequestMapping(value = "/addEmployee", method= RequestMethod.POST)
    public String addEmployee(@Valid @ModelAttribute(value = "employee") Employees employees, Model model)
    {

        List<Hotel> hotelStringList= hotelRepo.findAll();
        model.addAttribute("hotels",hotelStringList);

        if(!employeeRepo.existsBynic(employees.getNic()))
        {

            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

            emailSenderService email = (emailSenderService) context.getBean("email");

            String senderEmailId = "finemarkcom@gmail.com";
            String receiverEmailId = employees.getEmailEmployee();
            String subject = "Confirmation of Registraion";
            String message = "Welcome \n"+employees.getFirstName()+" "+ employees.getLastName()+"\n"
                    +"Congrulations you have been got registered as our new " +employees.getEmployeeRole();

            email.sendEmail(senderEmailId, receiverEmailId, subject, message);
            employeeRepo.save(employees);
            model.addAttribute("Sucessfully","Sucessfully Regsitered!");
        }
        else{
            model.addAttribute("failure","Already existing employee!");

        }
        return "employee";

    }



}
