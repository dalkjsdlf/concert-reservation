package io.hpp.concertreservation.concert.respository;

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
import io.hpp.concertreservation.initdata.InitData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

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
@SpringBootTest
@Transactional
@ComponentScan(basePackages = {"io.hpp.concertreservation.biz.domain"})
public class ReservationCoreRepositoryTest {


    private final IScheduleStoreRepository scheduleStoreRepository;
    private final IConcertStoreRepository concertStoreRepository;

    private final ISeatStoreRepository seatStoreRepository;
    private final ISeatLoadRepository seatLoadRepository;

    private final IReservationLoadRepository reservationLoadRepository;

    private final IReservationStoreRepository reservationStoreRepository;

    public final InitData initData;

    public ReservationCoreRepositoryTest(@Autowired IScheduleLoadRepository scheduleLoadRepository,
                                         @Autowired IScheduleStoreRepository scheduleStoreRepository,
                                         @Autowired IConcertStoreRepository concertStoreRepository,
                                         @Autowired ISeatStoreRepository seatStoreRepository,
                                         @Autowired ISeatLoadRepository seatLoadRepository,
                                         @Autowired IReservationLoadRepository reservationLoadRepository,
                                         @Autowired IReservationStoreRepository reservationStoreRepository,
                                         @Autowired InitData initData) {
        this.scheduleStoreRepository = scheduleStoreRepository;
        this.concertStoreRepository = concertStoreRepository;
        this.seatStoreRepository = seatStoreRepository;
        this.seatLoadRepository = seatLoadRepository;
        this.reservationLoadRepository = reservationLoadRepository;
        this.reservationStoreRepository = reservationStoreRepository;
        this.initData                   = initData;
    }

    private Long phsConcertScheduleId = 0L;
    private Long phsConcertSecondScheduleId = 0L;

    private Long concertId = 0L;

    private List<Seat> seats = List.of();

    @DisplayName("박효신 콘서트 정보 입력, 스케쥴 2023-12-24 17시, 21시 공연 스케쥴 입력, 17시 3석, 21시 2석 입력")
    @BeforeEach
    void init(){
        concertId = initData.initDataForConcert().getId();
        phsConcertScheduleId = initData.initDataForSchedule(concertId).getId();
        seats = initData.initDataForSeat(phsConcertScheduleId);
    }

    @DisplayName("Not null 체크")
    @Test()
    public void given_when_thenNotNull(){
        // given

        // when

        // then

        assertThat(scheduleStoreRepository).isNotNull();
        assertThat(concertStoreRepository).isNotNull();
        assertThat(seatStoreRepository).isNotNull();
        assertThat(seatLoadRepository).isNotNull();
        assertThat(reservationLoadRepository).isNotNull();
        assertThat(reservationStoreRepository).isNotNull();


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
        Reservation result = reservationStoreRepository.saveReservation(reservation);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(userId);
    }

    @DisplayName("[성공] userId로 예약 내역 조회하기")
    @Test()
    public void givenUserId_whenGetReservations_thenReservations(){
        // given
        Long userId = 1L;
        int beforeSize = reservationLoadRepository.findReservationsByUserId(userId).size();

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

        reservationStoreRepository.saveReservation(reservation);
        reservationStoreRepository.saveReservation(reservation2);
        int afterSize = beforeSize + 2;
        // when
        List<Reservation> reservations = reservationLoadRepository.findReservationsByUserId(userId);

        // then
        assertThat(reservations.size()).isEqualTo(afterSize);

    }
}
