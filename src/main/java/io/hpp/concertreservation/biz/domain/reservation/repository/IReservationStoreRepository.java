package io.hpp.concertreservation.biz.domain.reservation.repository;

import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;

public interface IReservationStoreRepository {

    public Reservation saveReservation(Reservation reservation);

    void deleteById(Long reserveId);

    public void delete(Reservation reservation);
}
