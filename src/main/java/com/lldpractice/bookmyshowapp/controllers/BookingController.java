package com.lldpractice.bookmyshowapp.controllers;

import com.lldpractice.bookmyshowapp.dto.BookMovieRequestDto;
import com.lldpractice.bookmyshowapp.dto.BookMovieResponseDto;
import com.lldpractice.bookmyshowapp.dto.ResponseStatus;
import com.lldpractice.bookmyshowapp.models.Booking;
import com.lldpractice.bookmyshowapp.services.BookingService;

public class BookingController {

        private BookingService bookingService;
        public BookMovieResponseDto bookMovie(BookMovieRequestDto request){
            BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();
            try{
                Booking booking =    bookingService.bookMovie(request.getShowSeatIds() , request.getUserId(),request.getShowId());
                bookMovieResponseDto.setBookingId(booking.getId());
                bookMovieResponseDto.setAmount(booking.getAmount());
                bookMovieResponseDto.setStatus(ResponseStatus.SUCCESS);
            }
            catch (Exception e){
                bookMovieResponseDto.setStatus(ResponseStatus.FAILURE);
            }
             return bookMovieResponseDto;
        }
}
