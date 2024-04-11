package io.hpp.concertreservation.biz.domain.reservation.infrastructure;

import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.reservation.repository.IReservationStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationCoreStoreRepository implements IReservationStoreRepository {
    private final IReservationJpaRepository reservationJpaRepository;

    public ReservationCoreStoreRepository(@Autowired IReservationJpaRepository reservationJpaRepository) {
        this.reservationJpaRepository = reservationJpaRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationJpaRepository.save(reservation);
    }

    @Override
    public void deleteById(Long reserveId) {
        reservationJpaRepository.deleteById(reserveId);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationJpaRepository.delete(reservation);
    }
}
