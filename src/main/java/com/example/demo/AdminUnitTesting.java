package com.example.demo;

import com.example.demo.enitity.Employees;
import com.example.demo.enitity.Hotel;
import com.example.demo.enitity.Room;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.repo.HotelRepo;
import com.example.demo.repo.RoomRepo;
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
    private RoomRepo roomRepo;



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
    public void getRoomList()
    {
        List<Room> roomList= roomRepo.findAll();
        Assert.assertNotNull(roomList);
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
    public void getCheckemployeeEmail()
    {
      Employees employees= new Employees("Hamdhan","Hassan","684 negambo road, mabola,wattala","07779290009",60.0,"waiter","1990099999V","mariyamappdev@gmail.com", hotelRepo.findByHotelID(8L));
      Assert.assertNotEquals("Hamdhan",employees.getEmailEmployee());

    }

    @Test
    public void getRegisterRoom()
    {
        Room room = new Room();
        room.setRoomId(63L);
        room.setRoomFloor(1);
        room.setStatus("AVAILABLE");
        room.setRoomDetails("Allows to accommodate two people with less price for one night stay,with Attached bathroom! ");
        room.setRoomNumber(101);
        room.setPriceOfRoom(16.00);
        room.setRoomType("Double Bedroom");
        room.setHotel(hotelRepo.findByHotelID(48L));
        roomRepo.save(room);
        Assert.assertNotNull(roomRepo.findById(63L));
    }

    @Test
    public void SingleRoom ()
    {
       Room room=  roomRepo.findById(37L).get();
       Assert.assertEquals("Allows to accommodate two people with less price for one night stay,with Attached bathroom! ",room.getRoomDetails());

    }


    @Test
    public void testUpdateHotel()
    {
        Hotel hotel = hotelRepo.findById(8L).get();
        hotel.setHotelPhoneNumber("011-2938358");
        hotelRepo.save(hotel);
        Assert.assertNotEquals(011-2938354,hotelRepo.findById(8L).get().getHotelPhoneNumber());

    }

    @Test
    public void getUpdateHotelName()
    {
        Hotel hotel = hotelRepo.findById(8L).get();
        hotel.setHotelName("Shnagrilla - pandura");
        hotelRepo.save(hotel);
        Assert.assertNotEquals("Hotel Shangrilla - Panadura",hotelRepo.findById(8L).get().getHotelName());
    }




    @Test
    public void getEmployeeRegister()
    {
        Employees employees = new Employees();
        employees.setFirstName("Zakir");
        employees.setLastName("Ahmed");
        employees.setEmployeeRole("waiter");
        employees.setAddress("68 Havelock road, Colombo 5");
        employees.setNic("1990099997V");
        employees.setPhoneNumber("0779290000");
        employees.setEmailEmployee("nabila@gmail.com");
        employees.setSalary(60.0);
        employees.setHotel(hotelRepo.findByHotelID(28L));
        employeeRepo.save(employees);
        Assert.assertNotNull(employeeRepo.getById(61L));
    }


    @Test
    public void getOneEmployee()
    {
        Employees employees=  employeeRepo.findById(61L).get();
        Assert.assertEquals("1990099997V",employees.getNic());
    }


    @Test
    public void getDeleteEmployee()
    {
        Employees employees = employeeRepo.getById(61L);
        employeeRepo.deleteById(employees.getEmployeeId());
        Assert.assertNotNull(employees);
    }

    @Test
    public void getEmployeePhoneUpdate()
    {
        Employees employees = employeeRepo.findById(61L).get();
        employees.setPhoneNumber("0779290000");
        employeeRepo.save(employees);
        Assert.assertNotEquals("0729290000",employeeRepo.findById(61L).get().getPhoneNumber());

    }

    @Test
    public void getEmployeeRoleUpdate()
    {
        Employees employees = employeeRepo.findById(61L).get();
        employees.setEmployeeRole("Chef");
        employeeRepo.save(employees);
        Assert.assertNotEquals("waiter",employeeRepo.findById(61L).get().getEmployeeRole());
    }

    @Test
    public void getEmployeeAddressUpdate()
    {
        Employees employees = employeeRepo.findById(61L).get();
        employees.setAddress("684 negambo road Mabola Wattala");
        employeeRepo.save(employees);
        Assert.assertNotEquals("68 Havelock road, Colombo 5",employeeRepo.findById(61L).get().getAddress());
    }



}
