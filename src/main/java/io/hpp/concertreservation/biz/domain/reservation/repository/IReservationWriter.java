package io.hpp.concertreservation.biz.domain.reservation.repository;

import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;

public interface IReservationWriter {

    public Reservation save(Reservation reservation);
}
