package io.hpp.concertreservation.biz.domain.reservation.infrastructure;

import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.reservation.repository.IReservationLoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Component
public class ReservationCoreLoadRepository implements IReservationLoadRepository {

    private final IReservationJpaRepository reservationJpaRepository;

    @Override
    public List<Reservation> findReservationsByUserId(Long userId) {
        return reservationJpaRepository.findByUserId(userId);
    }

    @Override
    public List<Reservation> findAllReservation() {
        return reservationJpaRepository.findAll();
    }

    @Override
    public Optional<Reservation> findReservationByScheduleId(Long scheduleId) {
        return reservationJpaRepository.findByScheduleId(scheduleId);
    }

    @Override
    public Optional<Reservation> findReservationById(Long reserveId) {
        return reservationJpaRepository.findById(reserveId);
    }
}
