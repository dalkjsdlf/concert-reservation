package io.hpp.concertreservation.concert.respository;

import io.hpp.concertreservation.biz.domain.concert.infrastructure.ConcertCoreRepository;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.schedule.infrastructure.ScheduleCoreRepository;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.seat.enumclass.SeatGrade;
import io.hpp.concertreservation.biz.domain.seat.infrastructure.SeatCoreRepository;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("좌석 정보 테스트")
@DataJpaTest
public class SeatCoreRepositoryTest {

    private final ScheduleCoreRepository scheduleCoreRepository;
    private final ConcertCoreRepository concertCoreRepository;
    private final SeatCoreRepository seatCoreRepository;
    public SeatCoreRepositoryTest(@Autowired ScheduleCoreRepository scheduleCoreRepository,
                                  @Autowired ConcertCoreRepository concertCoreRepository,
                                  @Autowired SeatCoreRepository seatCoreRepository) {
        this.scheduleCoreRepository = scheduleCoreRepository;
        this.concertCoreRepository  = concertCoreRepository;
        this.seatCoreRepository     = seatCoreRepository;
    }

    private Long phsConcertScheduleId = 0L;
    private Long phsConcertSecondScheduleId = 0L;

    private Long phsConcertSeatId;

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

        Seat savedSeat1 = seatCoreRepository.save(seat1);
        seatCoreRepository.save(seat2);
        seatCoreRepository.save(seat3);
        seatCoreRepository.save(seat4);
        seatCoreRepository.save(seat5);

        phsConcertSeatId = savedSeat1.getId();
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
    }

    @DisplayName("[성공] 박효신 콘서트 17시 콘서트 좌석 조회 : 예상 결과값 3")
    @Test()
    public void givenScheduleId_whenGetAllSeat_thenSeats(){
        // given
        // phsConcertScheduleId

        // when
        List<Seat> seats = seatCoreRepository.findByScheduleId(phsConcertScheduleId);

        // then
        assertThat(seats.size()).isEqualTo(3);
        assertThat(seats.get(0).getScheduleId()).isEqualTo(phsConcertScheduleId);
    }

    @DisplayName("[성공] 박효신 콘서트 21시 콘서트 좌석 조회 : 예상 결과값 2")
    @Test()
    public void givenSecondScheduleId_whenGetAllSeat_thenSeats(){
        // given
        // phsConcertSecondScheduleId

        // when
        List<Seat> seats = seatCoreRepository.findByScheduleId(phsConcertSecondScheduleId);

        // then
        assertThat(seats.size()).isEqualTo(2);
        assertThat(seats.get(0).getScheduleId()).isEqualTo(phsConcertSecondScheduleId);
    }

    @DisplayName("[성공] reservationId update 테스트")
    @Test()
    public void givenReservationId_whenUpdateReservationid_thenSuccessfullyUpdate(){
        // given
        // seatId : phsConcertSeatId
        Long reservationId = 1L;
        Optional<Seat> optSeat = seatCoreRepository.findById(phsConcertSeatId);
        Seat seat = optSeat.orElseThrow(()->new ReservationException(ReservationErrorResult.NO_SEATS));

        // when
        seat.setReserveId(reservationId);
        Seat savedSeat = seatCoreRepository.save(seat);

        // then
        assertThat(savedSeat).isNotNull();
        assertThat(savedSeat.getReserveId()).isEqualTo(reservationId);
    }
}