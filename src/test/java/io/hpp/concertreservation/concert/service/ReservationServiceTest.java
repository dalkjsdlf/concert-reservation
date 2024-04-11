package io.hpp.concertreservation.concert.service;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertWriter;
import io.hpp.concertreservation.biz.domain.reservation.service.ReservationService;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.schedule.repository.IScheduleWriter;
import io.hpp.concertreservation.biz.domain.seat.enumclass.SeatGrade;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @desc 시나리오
 * 박효신 크리스마스 콘서트 21시 공연 1번과 2번 좌석을 예매한다.
 * 사전준비
 * 1) 박효신 크리스마스 콘서트를 등록
 * 2) 박효신 24일 17시, 21시 스케쥴 등록
 * 3) 17시 스케쥴 좌석 1(VIP), 2(R), 3(R)
 * 4) 17시 스케쥴 좌석 1,2번 좌석 예약(실제 테스트 코드)
 * 5) 결제 대기 상태
 * */
@DisplayName("[예약 서비스 테스트]")
@SpringBootTest
public class ReservationServiceTest {
    private final IConcertWriter concertWriter;

    private final IScheduleWriter scheduleWriter;

    private final ISeatWriter seatWriter;

    private final ReservationService reservationService;

    public ReservationServiceTest(@Autowired IConcertWriter concertWriter,
                                  @Autowired IScheduleWriter scheduleWriter,
                                  @Autowired ISeatWriter seatWriter,
                                  @Autowired ReservationService reservationService) {
        this.concertWriter = concertWriter;
        this.scheduleWriter = scheduleWriter;
        this.seatWriter = seatWriter;
        this.reservationService = reservationService;
    }

    Long phsScheduleId = 0L;
    Long phsConcertId = 0L;

    @BeforeEach
    void init(){
        Concert phsConcert = Concert.of("박효신 콘서트",
                "박효신의 크리스마스 콘서트!",
                "박효신",
                LocalDateTime.of(2023,12,24,0,0,0),
                LocalDateTime.of(2023,12,25,0,0,0));

        Concert savedPhsConcert = concertWriter.writeConcert(phsConcert);
        phsConcertId = savedPhsConcert.getId();

        Schedule phsSchedule1 = Schedule.of(
                phsConcertId,
                LocalDateTime.of(2023,12,24,17,0,0));

        Schedule phsSchedule = scheduleWriter.writeSchedule(phsSchedule1);

        phsScheduleId = phsSchedule.getId();

        Seat seat1 = Seat.of(phsScheduleId,1L, SeatGrade.VIP,240000L,-1L);
        Seat seat2 = Seat.of(phsScheduleId,2L, SeatGrade.R,120000L,-1L);
        Seat seat3 = Seat.of(phsScheduleId,3L, SeatGrade.R,120000L,-1L);

        seatWriter.writeSeat(seat1);
        seatWriter.writeSeat(seat2);
        seatWriter.writeSeat(seat3);
    }

    @DisplayName("[성공] 예약 테스트")
    @Test()
    public void given_when_then(){
        // given
        Long userId = 1L;
        List<Long> seatIds = List.of(phsScheduleId);
        // when
        //Reservation reservation = reservationService.reserveConcert(phsScheduleId,seatIds,userId);

        //List<Reservation reservation1 = reservationService.getReservationsByUserId(userId);
        // then
        //assertThat(reservation).isEqualTo(reservation1);


    }




}
