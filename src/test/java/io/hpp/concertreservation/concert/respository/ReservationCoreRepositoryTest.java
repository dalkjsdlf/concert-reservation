package io.hpp.concertreservation.concert.respository;

import io.hpp.concertreservation.biz.domain.concert.infrastructure.ConcertCoreRepository;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.reservation.enumclass.PaymentStatus;
import io.hpp.concertreservation.biz.domain.reservation.infrastructure.ReservationCoreRepository;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.schedule.infrastructure.ScheduleCoreRepository;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.seat.enumclass.SeatGrade;
import io.hpp.concertreservation.biz.domain.seat.infrastructure.SeatCoreRepository;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 *** 사전 준비
 * 공연정보 입력
 * 스케쥴정보 입력
 * 좌석정보 입력
 *
 * 테스트1 예약정보 입력하기
 * 테스트2 입력된 예약 정보 예약ID로 조회하기
 * 테스트3 입력된 예약 정보 사용자 ID로 조회하기
 * */
@DisplayName("예약 DB 테스트")
@DataJpaTest
public class ReservationCoreRepositoryTest {

    private final ScheduleCoreRepository scheduleCoreRepository;
    private final ConcertCoreRepository concertCoreRepository;

    private final SeatCoreRepository seatCoreRepository;

    private final ReservationCoreRepository reservationCoreRepository;

    public ReservationCoreRepositoryTest(@Autowired ScheduleCoreRepository scheduleCoreRepository,
                                         @Autowired ConcertCoreRepository concertCoreRepository,
                                         @Autowired SeatCoreRepository seatCoreRepository,
                                         @Autowired ReservationCoreRepository reservationCoreRepository) {
        this.scheduleCoreRepository = scheduleCoreRepository;
        this.concertCoreRepository  = concertCoreRepository;
        this.seatCoreRepository     = seatCoreRepository;
        this.reservationCoreRepository = reservationCoreRepository;
    }

    private Long phsConcertScheduleId = 0L;
    private Long phsConcertSecondScheduleId = 0L;

    @DisplayName("박효신 콘서트 정보 입력, 스케쥴 2023-12-24 17시, 21시 공연 스케쥴 입력, 17시 3석, 21시 2석 입력")
    @BeforeEach
    void init(){
        /**
         * 콘서트 정보 입력하기
         * */
        Concert phsConcert = Concert.of("박효신 콘서트",
                "박효신의 크리스마스 콘서트!",
                "박효신",
                LocalDateTime.of(2023,12,24,0,0,0),
                LocalDateTime.of(2023,12,25,0,0,0));

        Concert savedPhsConcert = concertCoreRepository.save(phsConcert);
        Long phsConcertId   = savedPhsConcert.getId();

        /**
         * 스케쥴 정보 입력하기
         * */
        Schedule phsSchedule1 = Schedule.of(
                phsConcertId,
                LocalDateTime.of(2023,12,24,17,0,0));
        scheduleCoreRepository.save(phsSchedule1);

        Schedule phsSchedule2 = Schedule.of(
                phsConcertId,
                LocalDateTime.of(2023,12,24,21,0,0));
        scheduleCoreRepository.save(phsSchedule2);
        phsConcertScheduleId       = phsSchedule1.getId();
        phsConcertSecondScheduleId = phsSchedule2.getId();
        /**
         * 좌석 정보 입력하기 1 ~ 50
         * 총 5개 입력하기
         * 박효신 콘서트 17시 공연 3석, 21시 공연 2석
         * */
        Seat seat1 = Seat.of(phsConcertScheduleId,1L, SeatGrade.VIP,240000L,-1L);
        Seat seat2 = Seat.of(phsConcertScheduleId,2L, SeatGrade.R,120000L,-1L);
        Seat seat3 = Seat.of(phsConcertScheduleId,3L, SeatGrade.R,120000L,-1L);
        Seat seat4 = Seat.of(phsConcertSecondScheduleId,1L, SeatGrade.VIP,250000L,-1L);
        Seat seat5 = Seat.of(phsConcertSecondScheduleId,2L, SeatGrade.R,120000L,-1L);

        seatCoreRepository.save(seat1);
        seatCoreRepository.save(seat2);
        seatCoreRepository.save(seat3);
        seatCoreRepository.save(seat4);
        seatCoreRepository.save(seat5);
    }

    @DisplayName("Not null 체크")
    @Test()
    public void given_when_thenNotNull(){
        // given

        // when

        // then
        assertThat(concertCoreRepository).isNotNull();
        assertThat(scheduleCoreRepository).isNotNull();
        assertThat(seatCoreRepository).isNotNull();
        assertThat(reservationCoreRepository).isNotNull();
    }

    @DisplayName("[성공] 박효신 24일 17시 공연 1번 좌석 예약 정보 입력")
    @Test()
    public void givenScheduleId_whenSaveReservation_thenSuccessfullySave(){
        // given
        Long userId = 1L;

        Reservation reservation = Reservation.of(
                userId,
                phsConcertScheduleId,
                LocalDateTime.now(),
                1,
                240000L,
                PaymentStatus.WAIT);

        // when
        Reservation result = reservationCoreRepository.save(reservation);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(userId);
    }

    @DisplayName("[성공] userId로 예약 내역 조회하기")
    @Test()
    public void givenUserId_whenGetReservations_thenReservations(){
        // given
        Long userId = 1L;

        Reservation reservation = Reservation.of(
                userId,
                phsConcertScheduleId,
                LocalDateTime.now(),
                1,
                240000L,
                PaymentStatus.WAIT);
        Reservation reservation2 = Reservation.of(
                userId,
                phsConcertSecondScheduleId,
                LocalDateTime.now(),
                1,
                240000L,
                PaymentStatus.WAIT);

        reservationCoreRepository.save(reservation);
        reservationCoreRepository.save(reservation2);

        // when
        List<Reservation> reservations = reservationCoreRepository.findByUserId(userId);

        // then
        assertThat(reservations.size()).isEqualTo(2);

    }
}
