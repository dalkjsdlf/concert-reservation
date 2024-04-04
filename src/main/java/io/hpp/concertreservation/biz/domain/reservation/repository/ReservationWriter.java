package io.hpp.concertreservation.biz.domain.reservation.repository;

import io.hpp.concertreservation.biz.domain.reservation.infrastructure.ReservationCoreRepository;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationWriter implements IReservationWriter{

    private final ReservationCoreRepository coreRepository;

    /**
     * 생성자
     * */
    public ReservationWriter( @Autowired ReservationCoreRepository coreRepository) {
        this.coreRepository = coreRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return coreRepository.save(reservation);
    }

    @Override
    public Reservation delete(Long reserveId) {
        coreRepository.deleteById(reserveId);
        return null;
    }
}
