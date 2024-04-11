package io.hpp.concertreservation.biz.api.reservation.usecase;

import io.hpp.concertreservation.biz.api.reservation.dto.ReservationRequestDto;
import io.hpp.concertreservation.biz.api.reservation.dto.ReservationResponseDto;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationCreator;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationModifier;
import io.hpp.concertreservation.biz.domain.reservation.model.PaymentStatus;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.seat.component.SeatModifier;
import io.hpp.concertreservation.biz.domain.seat.component.SeatReader;
import io.hpp.concertreservation.biz.domain.seat.component.SeatSupportor;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional()
public class ReserveConcertUseCase {

    private final SeatReader seatReader;
    private final SeatSupportor seatSupportor;

    private final ReservationCreator reservationCreator;

    private final SeatModifier seatModifier;

    private final ReservationModifier reservationModifier;

    public ReserveConcertUseCase(@Autowired SeatReader seatReader,
                                 @Autowired SeatSupportor seatSupportor,
                                 @Autowired ReservationCreator reservationCreator,
                                 @Autowired SeatModifier seatModifier,
                                 @Autowired ReservationModifier reservationModifier) {
        this.seatReader = seatReader;
        this.seatSupportor = seatSupportor;
        this.reservationCreator = reservationCreator;
        this.seatModifier = seatModifier;
        this.reservationModifier = reservationModifier;
    }

    public void execute(ReservationRequestDto reservationRequestDto){
        /**
         * 요청자 ID
         * */
        Long userId        = reservationRequestDto.getUserId();
        List<Seat> seats   = reservationRequestDto.getSeats();

        /**
         * 좌석목록 정보로부터 스케쥴ID 조회
         * */
        Long scheduleId = seatReader.readScheduleIdOfSeats(seats);

        /**
         * 좌석들의 가격 합계
         * */
        Long totalPrice = seatSupportor.sumTotalPrice(seats);

        /**
         * 예약 객체 생성
         * */
        Reservation reservation = ReservationCreator.create(
                userId,
                scheduleId,
                seats.size(),
                totalPrice,
                PaymentStatus.WAIT);

        /**
         * 예약 정보 추가
         * */
        Reservation addReservation = reservationModifier.addReservation(reservation);

        /**
         * 좌석정보에 예약 처리
         * */
        seatModifier.reserveSeats(seats, addReservation.getId());
    }
}
