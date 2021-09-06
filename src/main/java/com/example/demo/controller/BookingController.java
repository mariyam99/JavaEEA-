package com.example.demo.controller;

import com.example.demo.emailSenderService;
import com.example.demo.enitity.Booking;
import com.example.demo.enitity.Room;
import com.example.demo.enitity.User;
import com.example.demo.repo.BookingRepo;
import com.example.demo.repo.HotelRepo;
import com.example.demo.repo.RoomRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class BookingController {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    @RequestMapping(value = "/searchRoom", method = RequestMethod.POST)
    public String addBooking(Model model, Room room,
                             @RequestParam(name="startDate",required = false) String startDate,
                             @RequestParam(name = "endDate",required = false) String endDate){


                List<Room> room1= roomRepo.findRoom(room);

                model.addAttribute("rooms",room1);
                model.addAttribute("avaible","The avaiable rooms");

                model.addAttribute("start",startDate);
                model.addAttribute("end",endDate);
                return "availableRooms";


    }

    @RequestMapping(value = "/reservation",method = RequestMethod.POST)
    public String reservationPage(@RequestParam(name = "roomID") Long id,Model model,
                                  @RequestParam(name="startDate",required = false) String startDate,
                                  @RequestParam(name = "endDate",required = false) String endDate) {

        Room room1=roomRepo.findByRoomId(id);
        model.addAttribute("room",room1);

        model.addAttribute("start",startDate);
        model.addAttribute("end",endDate);

        return "reservationform";
    }

    @Transactional
    @RequestMapping(value = "/addReservation",method = RequestMethod.POST)
    public String addreservation(Model model,@RequestParam(name = "roomId",required = false) Long roomid,
                                 Booking booking,HttpServletRequest request,
                                 @RequestParam(name="startDate",required = false) String startDate,
                                 @RequestParam(name = "endDate",required = false) String endDate) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
        Date start = dateFormat.parse(startDate);
        Date end = dateFormat.parse(endDate);

        HttpSession session= request.getSession();
        String username = (String) session.getAttribute("userName");

            if (userRepo.existsByUserName(username)) {
                User user1 = userRepo.findByUserName(username);
                booking.setUser(user1);


                Room room = roomRepo.findByRoomId(roomid);
                booking.setRoom(room);
                booking.setStartDate(start);
                booking.setEndDate(end);


                ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

                emailSenderService email = (emailSenderService) context.getBean("email");

                String senderEmailId = "finemarkcom@gmail.com";
                String receiverEmailId = user1.getEmail();
                String subject = "Confirmation of Booked Hotel";
                String message = "Welcome \n"+user1.getUserName()+" "
                        +"Confirmation of your booked room "+"Booking Details are: "+" " +
                        "Booked Room Type " +room.getRoomType() +" "+
                        "Booked Room Number "+room.getRoomNumber() +" "+
                        "Speacial Request Made" + booking.getSpecialRequest()+" " +
                        "Hotel Reserved the room at " + room.getHotel().getHotelName() +" "+
                        "Hotel Address " + room.getHotel().getHotelAddress()+" "+
                        "Check in date " +booking.getStartDate()+" "+
                        "Check out date " + booking.getEndDate();

                email.sendEmail(senderEmailId, receiverEmailId, subject, message);

                bookingRepo.save(booking);
                model.addAttribute("Sucessfully", "Sucessfully reserved");
                return "bookingView";

            }
            else {
                model.addAttribute("invalid", "Please login again");
                return "customerHome";

            }

    }

    @RequestMapping("/viewBook")
    public String myBooking(Model model,HttpServletRequest request,Booking booking)
    {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userName");

        if(userRepo.existsByUserName(username))
        {
            User user= userRepo.findByUserName(username);
            booking.setUser(user);

            List<Booking> booking1= bookingRepo.findAll();

            model.addAttribute("booking",booking1);
            return "bookingView";
        }
        else
        {
            model.addAttribute("invalid","Please try again..login");
            return "customerHome";
        }

    }


    @RequestMapping("/manageBooking")
    public String bookings(Model model)
    {
        model.addAttribute("bookinglist",bookingRepo.findAll());
        return "manageBooking";
    }





}
