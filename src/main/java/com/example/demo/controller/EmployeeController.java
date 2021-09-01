package com.example.demo.controller;

import com.example.demo.emailSenderService;
import com.example.demo.enitity.Employees;
import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.User;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.HotelRepo;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;


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
    public String addEmployee(Employees employees, Model model,
                              @RequestParam(name = "id") Long id)
    {

        List<Hotel> hotelStringList= hotelRepo.findAll();
        model.addAttribute("hotels",hotelStringList);

        Hotel hotel= hotelRepo.findByHotelID(id);
        employees.setHotel(hotel);

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


    @RequestMapping("/viewEmployee")
    public String listemployees(Model model){

        model.addAttribute("employeelist", employeeRepo.findAll());
        return "viewEmployee";

    }

    @Transactional
    @RequestMapping(value = "/deleteEmployee",method = RequestMethod.POST)
    public String deleteEmployee(Employees employees,Model model)
    {
        Employees employees1= employeeRepo.getById(employees.employeeId);
        employeeRepo.deleteById(employees1.getEmployeeId());

        model.addAttribute("sucessDeleteEmployee",employees1.getFirstName()+"\t removed from list");
        return "redirect:/viewEmployee";

    }

    @Transactional
    @RequestMapping(value = "/updateEmployee",method = RequestMethod.POST)
    public String updateEmployee(Employees employees, Model model)
    {

        Employees employees1=employeeRepo.getById(employees.getEmployeeId());

        if(employees.getEmployeeRole()=="")
        {
            employees.setEmployeeRole(null);
        }

        if(employees.getPhoneNumber()=="")
        {
            employees.setPhoneNumber(null);
        }

        if(employees.getAddress() == "")
        {
                employees.setAddress(null);
        }

        if(employees.getEmployeeRole() != null) {
                employees1.setEmployeeRole(employees.getEmployeeRole());

        }
            if(employees.getPhoneNumber() != null)
            {
                employees1.setPhoneNumber(employees.getPhoneNumber());
            }

            if(employees.getAddress()!= null)
            {
                employees1.setAddress(employees.getAddress());
            }

            employeeRepo.updateEmployee(employees1);
            model.addAttribute("sucessUpdated","Profile updated");
            return "redirect:/viewEmployee";

        }




    }







