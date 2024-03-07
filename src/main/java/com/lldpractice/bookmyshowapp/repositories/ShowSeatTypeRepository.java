package com.lldpractice.bookmyshowapp.repositories;

import com.lldpractice.bookmyshowapp.models.Show;
import com.lldpractice.bookmyshowapp.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {

    List<ShowSeatType> findAllByShow(Show show);

}
