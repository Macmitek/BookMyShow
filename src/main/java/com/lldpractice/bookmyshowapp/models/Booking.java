package com.lldpractice.bookmyshowapp.models;

import jakarta.persistence.Entity;

import java.util.Date;
import java.util.List;

@Entity

public class Booking extends  BaseModel {
    private BookingStatus bookingStatus;
    private List<ShowSeat> showSeats;
    private User user;
    private Date bookedAt;
    private Show show;
    private  int amount;
    private  List<Payment> payments;
}
