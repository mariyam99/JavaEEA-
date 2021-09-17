package com.example.demo;

import com.example.demo.enitity.Employees;
import com.example.demo.enitity.Hotel;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.HotelRepo;
import com.example.demo.repo.UserRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdminUnitTesting {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private UserRepo userRepo;



    @Test
    public void getlisthotels()
    {
        List<Hotel> hotelList = hotelRepo.findAll();
        Assert.assertNotNull(hotelList);
    }


    @Test
    public void getlistemployees()
    {
        List<Employees> employeesList = employeeRepo.findAll();
        Assert.assertNotNull(employeesList);
    }

    @Test
    public void getprocessHotelRegister()
    {
        Hotel hotel= new Hotel(8L,"Hotel Shangrilla - Kelaniya","Kelaniya","011-2938344","No.69 Negambo Road,Kelaniya.");
        Assert.assertNotNull(hotel);
    }

    @Test
    public void getdeleteHotel()
    {
        Hotel hotel = hotelRepo.getById(8L);
        hotelRepo.deleteById(hotel.getHotelID());
        Assert.assertNotNull(hotel);
    }

    @Test
    public void getupdateHotel()
    {
        int hotel1 = hotelRepo.updateHotel("011-2938354", 8L);
        Assert.assertNotNull(hotel1);
    }

    @Test
    public void getexistsEmployeeByNic()
    {
        boolean employees1= employeeRepo.existsBynic("1990099999V");
        Assert.assertNotNull("1990099999V",employees1);
    }

    @Test
    public void getregisterEmployee()
    {
      Employees employees= new Employees("Hamdhan","Hassan","684 negambo road, mabola,wattala","07779290009",60.0,"waiter","1990099999V","mariyamappdev@gmail.com", hotelRepo.findByHotelID(8L));
      Assert.assertEquals("Hamdhan",employees.getFirstName());

    }




}
