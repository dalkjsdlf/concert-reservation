package io.hpp.concertreservation.testfortest;

import io.hpp.concertreservation.biz.domain.seat.infrastructure.ISeatJpaRepository;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.model.SeatGrade;
import io.hpp.concertreservation.initdata.InitData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("좌석 update를 위해 save 하였을 때 update가 아닌 insert가 쳐지고 있는지 확인 테스트")
@SpringBootTest
public class SeatDupTest {

    private final ISeatJpaRepository seatJpaRepository;
    private final InitData initData;

    private List<Seat> seats;

    public SeatDupTest(@Autowired ISeatJpaRepository seatJpaRepository,
                       @Autowired InitData initData) {
        this.seatJpaRepository = seatJpaRepository;
        this.initData = initData;
    }

    @BeforeEach
    void init(){
        Long concertId = initData.initDataForConcert().getId();
        Long scheduleId = initData.initDataForSchedule(concertId).getId();
        seats = initData.initDataForSeat(scheduleId);
    }

    @DisplayName("Not null")
    @Test
    public void given_whenCheckNotNull_then(){
        //given

        //when

        //then
        assertThat(seatJpaRepository).isNotNull();
    }

    @DisplayName("")
    @Test
    public void given_when_then(){
        //given
        Seat seat = Seat.of(3L,1L, SeatGrade.R, 10000L, 1L);
        Seat result = seatJpaRepository.save(seat);

        Seat foundSeat = seatJpaRepository.findById(result.getId()).orElseThrow();

        foundSeat.setScheduleId(2L);
        //when

        //then
        assertThat(foundSeat.getScheduleId()).isEqualTo(2L);
    }

    @DisplayName("init date 테스트")
    @Test
    public void givenInitData_whenCheck_then(){
        //given


        //when


        //then
        assertThat(seats.size()).isEqualTo(3);
    }
}
