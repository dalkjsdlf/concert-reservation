package io.hpp.concertreservation.concert.controller;

import com.google.gson.Gson;
import io.hpp.concertreservation.biz.api.reservation.controller.ReservationController;
import io.hpp.concertreservation.biz.api.reservation.dto.ReservationRequestDto;
import io.hpp.concertreservation.biz.domain.seat.component.SeatReader;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.common.exception.ApiControllerAdvice;
import io.hpp.concertreservation.initdata.InitData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Reservation Controller Test")
@SpringBootTest
@Transactional
public class ReservationControllerReserveTest {

    private static Logger logger = LoggerFactory.getLogger(ReservationControllerReserveTest.class);
    private MockMvc mockMvc;

    private final ReservationController reservationController;

    private final SeatReader seatReader;


    private final InitData initData;

    private Long scheduleId;
    private Long userId = 1L;
    private Gson gson;

    public ReservationControllerReserveTest(@Autowired ReservationController reservationController,
                                            @Autowired SeatReader seatReader,
                                            @Autowired InitData initData) {
        this.reservationController = reservationController;
        this.seatReader = seatReader;
        this.initData = initData;
    }

    @BeforeEach
    public void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController)
                .setControllerAdvice(new ApiControllerAdvice())
                .build();

        initDataInput();
    }

    @DisplayName("[성공] 예약내역 조회")
    @Test()
    public void givenUserId_whenGetReservations_thenReservation() throws Exception {
        // given
        Long userId = 1L;
        String url = "/api/reservations";

        List<Seat> seats = seatReader.readSeatsByScheduleId(scheduleId);

        String s = gson.toJson(ReservationRequestDto.builder().seats(seats).build());
        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .header(USER_ID_HEADER, 1L)
                .header(TOKEN_HEADER, "1L")
                .content(gson.toJson(ReservationRequestDto.builder().seats(seats).build()))
                .contentType(MediaType.APPLICATION_JSON));
        logger.info("json >> {}" , s);
        // then
        resultActions.andExpect(status().isCreated());
        //resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].reservationId").value(1L));
        //resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].concertName")  .value("박효신 콘서트"));
    }

    private void initDataInput() {
        Long concertId = initData.initDataForConcert().getId();
        scheduleId = initData.initDataForSchedule(concertId).getId();
        initData.initDataForSeat(scheduleId);
    }
}
