package com.lldpractice.bookmyshowapp.services;

import com.lldpractice.bookmyshowapp.models.Show;
import com.lldpractice.bookmyshowapp.models.ShowSeat;
import com.lldpractice.bookmyshowapp.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    private final ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    public int calculatePrice(Show show, List<ShowSeat> showSeats) {


        return 0;
    }
}