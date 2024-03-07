package com.lldpractice.bookmyshowapp.repositories;

import com.lldpractice.bookmyshowapp.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
}
