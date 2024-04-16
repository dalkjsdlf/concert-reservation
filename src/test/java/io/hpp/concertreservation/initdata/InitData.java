package io.hpp.concertreservation.initdata;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertStoreRepository;
import io.hpp.concertreservation.biz.domain.reservation.model.PaymentStatus;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.reservation.repository.IReservationLoadRepository;
import io.hpp.concertreservation.biz.domain.reservation.repository.IReservationStoreRepository;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.schedule.repository.IScheduleLoadRepository;
import io.hpp.concertreservation.biz.domain.schedule.repository.IScheduleStoreRepository;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.model.SeatGrade;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatLoadRepository;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class InitData {

    private final IScheduleLoadRepository scheduleLoadRepository;
    private final IScheduleStoreRepository scheduleStoreRepository;
    private final IConcertStoreRepository concertStoreRepository;

    private final ISeatStoreRepository seatStoreRepository;
    private final ISeatLoadRepository seatLoadRepository;

    private final IReservationLoadRepository reservationLoadRepository;

    private final IReservationStoreRepository reservationStoreRepository;

    public InitData(@Autowired IScheduleLoadRepository scheduleLoadRepository,
                                         @Autowired IScheduleStoreRepository scheduleStoreRepository,
                                         @Autowired IConcertStoreRepository concertStoreRepository,
                                         @Autowired ISeatStoreRepository seatStoreRepository,
                                         @Autowired ISeatLoadRepository seatLoadRepository,
                                         @Autowired IReservationLoadRepository reservationLoadRepository,
                                         @Autowired IReservationStoreRepository reservationStoreRepository) {
        this.scheduleLoadRepository = scheduleLoadRepository;
        this.scheduleStoreRepository = scheduleStoreRepository;
        this.concertStoreRepository = concertStoreRepository;
        this.seatStoreRepository = seatStoreRepository;
        this.seatLoadRepository = seatLoadRepository;
        this.reservationLoadRepository = reservationLoadRepository;
        this.reservationStoreRepository = reservationStoreRepository;
    }

    public Concert initDataForConcert(){
        /**
         * 콘서트 정보 입력하기
         * */
        Concert phsConcert = Concert.of("박효신 콘서트",
                "박효신의 크리스마스 콘서트!",
                "박효신",
                LocalDateTime.of(2023,12,24,0,0,0),
                LocalDateTime.of(2023,12,25,0,0,0));

        Concert savedPhsConcert = concertStoreRepository.saveConcert(phsConcert);

        return savedPhsConcert;
    }

    public Schedule initDataForSchedule(Long phsConcertId){

        /**
         * 스케쥴 정보 입력하기
         * */
        Schedule phsSchedule1 = Schedule.of(
                phsConcertId,
                LocalDateTime.of(2023,12,24,17,0,0));
        scheduleStoreRepository.saveSchedule(phsSchedule1);

        return phsSchedule1;
    }
    public List<Seat> initDataForSeat(Long phsConcertScheduleId){
        /**
         * 좌석 정보 입력하기 1 ~ 50
         * 총 5개 입력하기
         * 박효신 콘서트 17시 공연 3석, 21시 공연 2석
         * */
        Seat seat1 = Seat.of(phsConcertScheduleId,1L, SeatGrade.VIP,240000L,-1L);
        Seat seat2 = Seat.of(phsConcertScheduleId,2L, SeatGrade.R,120000L,-1L);
        Seat seat3 = Seat.of(phsConcertScheduleId,3L, SeatGrade.R,120000L,-1L);

        Seat savedSeat1 = seatStoreRepository.saveSeat(seat1);
        seatStoreRepository.saveSeat(seat2);
        seatStoreRepository.saveSeat(seat3);

        return List.of(seat1,seat2,seat3);
    }

    public Reservation initDataForReserve(Long userId, Long scheduleId){
        Reservation reservation = Reservation.of(
                userId,
                scheduleId,
                LocalDateTime.now(),
                1,
                240000L,
                PaymentStatus.WAIT);

        // when
        Reservation result = reservationStoreRepository.saveReservation(reservation);

        return reservation;
    }

    public void initDataForCharge(){

    }

}
