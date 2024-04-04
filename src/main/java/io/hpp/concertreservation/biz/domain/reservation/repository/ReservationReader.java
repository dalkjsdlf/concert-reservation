package io.hpp.concertreservation.biz.domain.reservation.repository;

import io.hpp.concertreservation.biz.domain.reservation.infrastructure.ReservationCoreRepository;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReservationReader implements IReservationReader{

    private ReservationCoreRepository coreRepository;

    /**
     * 생성자
     * */
    public ReservationReader(@Autowired ReservationCoreRepository coreRepository) {
        this.coreRepository = coreRepository;
    }

    @Override
    public List<Reservation> readReservationsByUserId(Long userId) {
        return coreRepository.findByUserId(userId);
    }

    @Override
    public Optional<Reservation> readReservationByScheduleId(Long scheduleId) {
        return coreRepository.findByScheduleId(scheduleId);
    }

    @Override
    public Optional<Reservation> readReservationById(Long reserveId) {
        return coreRepository.findById(reserveId);
    }
}
