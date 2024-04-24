package io.hpp.concertreservation.concert.simultaneity;

import io.hpp.concertreservation.biz.api.reservation.usecase.GetAllReservationsUseCase;
import io.hpp.concertreservation.biz.api.reservation.usecase.ReserveConcertUseCase;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationReader;
import io.hpp.concertreservation.biz.domain.seat.component.SeatReader;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.initdata.InitData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("콘서트 예약 동시성 테스트")
@SpringBootTest
@Transactional
@Slf4j
@Disabled
public class SimultaneityTest {

    //private final static Logger logger = LoggerFactory.getLogger(SimultaneityTest.class);

    private final ReserveConcertUseCase reserveConcertUseCase;
    private final SeatReader seatReader;
    private final InitData initData;

    private final ReservationReader reservationReader;


    private Long scheduleId;

    public SimultaneityTest(@Autowired ReserveConcertUseCase reserveConcertUseCase,
                            @Autowired SeatReader seatReader,
                            @Autowired InitData initData,
                            @Autowired ReservationReader reservationReader
    ) {
        this.reserveConcertUseCase = reserveConcertUseCase;
        this.seatReader = seatReader;
        this.initData = initData;

        this.reservationReader = reservationReader;
    }

    @BeforeEach
    void init(){
        Long concertId  = initData.initDataForConcert().getId();
        scheduleId = initData.initDataForSchedule(concertId).getId();
        initData.initDataForSeat(scheduleId);
    }

    @DisplayName("[성공] 여러 사용자가 동시에 예약하여도 순차 처리됨")
    @Test()
    public void givenManyUser_whenReserveSimultaneously_thenSyncResult() throws InterruptedException {
        // given
        List<Seat> seats = seatReader.readSeatsByScheduleId(scheduleId);

        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CountDownLatch latch = new CountDownLatch(threadCount);

        Long userIdSeq = 0L;
        //when
        for (int i = 0; i < threadCount; i++) {
            Long finalUserIdSeq = userIdSeq;
            executorService.submit(() -> {
                try {
                    log.info("Thread ID [{}],   userId >>>[{}]",Thread.currentThread().getId(), finalUserIdSeq);
                    reserveConcertUseCase.execute(seats, finalUserIdSeq);
                }finally {
                    latch.countDown();
                }
            });
            userIdSeq++;
        }
        latch.await();

        int cnt = reservationReader.readAllReservation().size();

        //Seat쪽에서 예외 터지고 예약 1건 입력

        assertThat(cnt).isEqualTo(1);
    }

}
