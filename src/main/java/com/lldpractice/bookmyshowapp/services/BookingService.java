package com.lldpractice.bookmyshowapp.services;

import com.lldpractice.bookmyshowapp.models.*;
import com.lldpractice.bookmyshowapp.repositories.BookingRepository;
import com.lldpractice.bookmyshowapp.repositories.ShowRepository;
import com.lldpractice.bookmyshowapp.repositories.ShowSeatRepository;
import com.lldpractice.bookmyshowapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private PriceCalculatorService priceCalculatorService;

    @Autowired
    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          BookingRepository bookingRepository,
                          PriceCalculatorService priceCalculatorService)
                           {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.priceCalculatorService = priceCalculatorService;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(List<Long> showSeatIds, Long userId, Long showId){

        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User bookedBy = userOptional.get();

        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new RuntimeException("Show not found");
        }
       Show bookedShow = showOptional.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat : showSeats){
            if(isShowSeatAvailable(showSeat)){
                throw new RuntimeException("Some of the show seats are not available!");
            }
            showSeat.setStatus(ShowSeatStatus.BLOCKED);
        }

        List<ShowSeat> updateShowSeats = showSeatRepository.saveAll(showSeats);

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(updateShowSeats);
        booking.setUser(bookedBy);
        booking.setBookedAt(new Date());
        booking.setShow(bookedShow);
        booking.setAmount(priceCalculatorService.calculatePrice(bookedShow, updateShowSeats));
        booking.setPayments(new ArrayList<>());

        return bookingRepository.save(booking);
    }
    private boolean isShowSeatAvailable(ShowSeat showSeat) {
        return showSeat.getStatus().equals(ShowSeatStatus.AVAILABLE) ||
                (showSeat.getStatus().equals(ShowSeatStatus.BLOCKED) &&
                        ChronoUnit.MINUTES.between(new Date().toInstant(), showSeat.getBlockedAt().toInstant()) > 15);
    }
}
