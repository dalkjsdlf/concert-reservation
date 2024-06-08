package io.hpp.concertreservation.concert.reservefail;

import io.hpp.concertreservation.biz.api.reservation.dto.ReservationResponseDto;
import io.hpp.concertreservation.biz.api.reservation.usecase.GetAllReservationsUseCase;
import io.hpp.concertreservation.biz.api.reservation.usecase.ReserveConcertUseCase;
import io.hpp.concertreservation.biz.api.seat.dto.SeatResponseDto;
import io.hpp.concertreservation.biz.api.seat.usecase.GetAllSeatsUseCase;
import io.hpp.concertreservation.biz.domain.seat.component.SeatReader;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import io.hpp.concertreservation.initdata.InitData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 시나리오 1.
 * 1) 두 사용자가 동일한 공연, 스케쥴, 좌석을 조회
 * 2) 두 사용자 모두 예약 가능좌석으로 조회됨
 * 3) 그 상태에서 한 사용자가 먼저 예약함
 * 4) 나머지 사용자가 예약 하려 하였으나 실패
 * 5) 이미 예약 된 좌석을 검증은 서버에서 체크(예약 로직에서 체크)
 * */
@DisplayName("이미 예약된 좌석 시나리오 1")
@SpringBootTest
@Slf4j
public class AlreadyReserveServerCheckTest {

    @Autowired
    private InitData initData;

    @Autowired
    private GetAllSeatsUseCase getAllSeatsUseCase;

    @Autowired
    private ReserveConcertUseCase reserveConcertUseCase;

    @Autowired
    private GetAllReservationsUseCase getAllReservationsUseCase;

    @Autowired
    private SeatReader seatReader;

    private Long scheduleId;

    private List<Seat> firstUserGetSeats;

    private Long firstUserId = 1L;

    private Long secondUserId = 2L;

    @BeforeEach
    void init(){
        Long concertId  = initData.initDataForConcert().getId();
        scheduleId = initData.initDataForSchedule(concertId).getId();

        /*
        * 1,2,3 번호 좌석 있음
        * **/
        initData.initDataForSeat(scheduleId);
    }

    @DisplayName("[ 성공 ] 순서1 USERID 1이 박효시 콘서트 12월24일 21시 1,2번 좌석을 조회")
    @Test()
    @Order(1)
    public void givenUser1_whenGetSeats_thenGetListSize3(){
        // given

        // when
        /*
        * 1,2,3 번 좌석이 조회됨
        * **/
        List<SeatResponseDto> resultSeats = getAllSeatsUseCase.executor(scheduleId);


        // then
        assertThat(resultSeats.size()).isEqualTo(3);
    }

    @DisplayName("[ 성공 ] 순서2 USERID 2가 박효시 콘서트 12월24일 21시 1,2번 좌석을 예약(USER 1과 동일한 좌석 조회 후 예약)")
    @Test()
    @Order(2)
    public void givenUser2_whenReserveSeatsSameWithUser1Selected_thenSuccessfullyReserve(){
        // given


        // when
        /*
        * 1,2,3 번 좌석이 조회됨
        * **/
        List<SeatResponseDto> resultSeats = getAllSeatsUseCase.executor(scheduleId);

        resultSeats.forEach(seat-> System.out.println(">>>>>>>> " + seat.getPrice()));

        /*
         * 실제로는 조회된 1,2,3 좌석중 1,2번 좌석을 예약할 것이라고 가정
         * **/

        SeatResponseDto seat1 = resultSeats.get(0);
        SeatResponseDto seat2 = resultSeats.get(1);

        /*
         * 첫 번째 좌석 선택 (좌석번호1)
         * **/
        Seat selectedSeat1 = Seat.of(scheduleId,
                seat1.getSeatNo(),
                seat1.getSeatGrade(),
                seat1.getPrice(),
                seat1.getReserveId());

        /*
         * 두 번째 좌석 선택 (좌석번호2)
         * **/
        Seat selectedSeat2 = Seat.of(scheduleId,
                seat2.getSeatNo(),
                seat2.getSeatGrade(),
                seat2.getPrice(),
                seat2.getReserveId());

        firstUserGetSeats = List.of(selectedSeat1, selectedSeat2);

        /*
         * 두 번째 사용자가 USER 1이 선택한 동일한 좌석을 먼저 예약한다.
         * */
        reserveConcertUseCase.execute(firstUserGetSeats,secondUserId);

        // then
        List<ReservationResponseDto> result = getAllReservationsUseCase.executor(secondUserId);
        assertThat(result.size()).isEqualTo(1);
    }

    @DisplayName("[ 실패 ] 순서3 USERID 1이 자신이 조회했었던 1,2번 좌석을 예약 - 이미 예약된 좌석입니다 예외")
    @Test()
    @Order(3)
    public void givenUser1_whenReserveSeatsSameWithUser2Reserved_thenSuccessfullyReserve(){
        // given
        /*
         * 1,2,3 번 좌석이 조회됨
         * **/
        List<Seat> resultSeats = seatReader.readSeatsByScheduleId(scheduleId);


        /*
         * 실제로는 조회된 1,2,3 좌석중 1,2번 좌석을 예약할 것이라고 가정
         * **/
        Seat selectedSeat1 = resultSeats.get(0);
        Seat selectedSeat2 = resultSeats.get(1);

        firstUserGetSeats = List.of(selectedSeat1, selectedSeat2);

        /*
         * 두 번째 사용자가 USER 1이 선택한 동일한 좌석을 먼저 예약한다.
         * */
        reserveConcertUseCase.execute(firstUserGetSeats,secondUserId);

        /*
         * 두 번째 사용자가 USER 1이 선택한 동일한 좌석을 먼저 예약한다.
         * */
        ReservationException reservationException = assertThrows(ReservationException.class, () -> reserveConcertUseCase.execute(firstUserGetSeats,firstUserId));

        /*
        흠...
        단계를 보자면
        1. 두 번째 사용자 좌석검사
          --> 같은 스케쥴의 좌석이 3건 조회될 것임
        2. 두 번째 사용자 예약 insert
        3. 두 번째 사용자 좌석 update
        4. 첫 번째 사용자 좌석검사
        5. 이미 예약된 좌석 exception
        에러는 seat가 2개이상 조회되서 에러났다는 건데 그 말은 두 번째 사용자가 좌석 검사할 때부터 문제가 되었어야 하는데 로그를 한번 찍어보자
        log 계획
          좌석 검사하는 component 기준으로 전 후 로그 찍을 예정
          [{}]가 좌석 검사하기 전, userId
          "[{}]가 좌석 검사한 후 조회된 좌석 개수 [{}]", userId, seats.size();
        * **/

        // then
        assertThat(reservationException.getErrorResult()).isEqualTo(ReservationErrorResult.ALREADY_SEAT_RESERVED);
    }

}
