package io.hpp.concertreservation.biz.domain.reservation.component;

import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.reservation.repository.IReservationLoadRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReservationReader{

    private final IReservationLoadRepository reservationLoadRepository;

    public ReservationReader(@Autowired IReservationLoadRepository reservationLoadRepository) {
        this.reservationLoadRepository = reservationLoadRepository;
    }

    public Reservation readReservationById(Long reservationId){
        Optional<Reservation> optReservation = reservationLoadRepository.findReservationById(reservationId);
        return optReservation.orElseThrow(()->new ReservationException(ReservationErrorResult.NO_RESERVATION));
    }

    public List<Reservation> readReservationsByUserId(Long userId){

        List<Reservation> reservations = reservationLoadRepository.findReservationsByUserId(userId);

//        if(reservations.isEmpty()){
//            throw new ReservationException(ReservationErrorResult.NO_RESERVATION);
//        }

        return reservations;
    }
}