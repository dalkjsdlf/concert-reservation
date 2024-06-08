package io.hpp.concertreservation.biz.api.reservation.usecase;

import io.hpp.concertreservation.biz.domain.reservation.component.ReservationCreator;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationModifier;
import io.hpp.concertreservation.biz.domain.reservation.model.PaymentStatus;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.seat.component.SeatModifier;
import io.hpp.concertreservation.biz.domain.seat.component.SeatReader;
import io.hpp.concertreservation.biz.domain.seat.component.SeatSupportor;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReserveConcertUseCase {

    private final SeatReader seatReader;
    private final SeatSupportor seatSupportor;
    private final SeatModifier seatModifier;
    private final ReservationModifier reservationModifier;
    private final RedissonClient redissonClient;


    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    public void execute(List<Seat> seats, Long userId){
        String key = "seatLock";
        RLock lock = redissonClient.getLock(key);

        try{
            lock.tryLock(5,2, TimeUnit.SECONDS);

            log.info("[{}] Thread가 ReserveUsecase 실행함",Thread.currentThread().getName());
            log.info("[{}] Thread가 좌석 검사하기 전",Thread.currentThread().getName());
            /**
             * 좌석들이 이미 예약이 되어 있는지 확인
             * */
            seatSupportor.checkAlreadyReservedSeatOfSeats(seats);
            log.info("[{}] Thread가 좌석 검사 후", Thread.currentThread().getName());

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

        }catch(InterruptedException e) {
            throw new ReservationException(ReservationErrorResult.UNKNOWN_EXCEPTION);
        }finally {
            lock.unlock();
        }
    }
}
