package com.lldpractice.bookmyshowapp.controllers;

import com.lldpractice.bookmyshowapp.dto.BookMovieRequestDto;
import com.lldpractice.bookmyshowapp.dto.BookMovieResponseDto;
import com.lldpractice.bookmyshowapp.dto.ResponseStatus;
import com.lldpractice.bookmyshowapp.models.Booking;
import com.lldpractice.bookmyshowapp.services.BookingService;

public class BookingController {

        private BookingService bookingService;
        public BookMovieResponseDto bookMovie(BookMovieRequestDto request){
             Booking booking =    bookingService.bookMovie(request.getShowSeatIds() , request.getUserId(),request.getShowId());
             BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
             bookMovieResponseDto.setBookingId(booking.getId());
             bookMovieResponseDto.setAmount(booking.getAmount());
             bookMovieResponseDto.setStatus(ResponseStatus.SUCCESS);
             return bookMovieResponseDto;
        }
}
