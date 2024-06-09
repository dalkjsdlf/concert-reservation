package io.hpp.concertreservation.biz.api.reservation.controller;

import com.google.gson.Gson;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("예약 컨트롤러 테스트")
@SpringBootTest
@ActiveProfiles("local")
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

    @DisplayName("[성공] 예약")
    @Test()
    public void givenUserId_whenGetReservations_thenReservation() throws Exception {
        // given
        Long userId = 1L;
        String url = "/api/reservations";

        /**
         * 예약 할 좌석 정보 받아보기
         * */
        List<Seat> seats = seatReader.readSeatsByScheduleId(scheduleId);

        /**
         * 입력할 JSON 데이터 생성
         * */
        String reservationJsonData = gson.toJson(ReservationRequestDto.builder().seats(seats).build());

        // when
        /**
         * Mock Mvc로 Request 생성
         * */
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .header(USER_ID_HEADER, 1L)
                .header(TOKEN_HEADER, "1L")
                .content(reservationJsonData)
                .contentType(MediaType.APPLICATION_JSON));

        logger.info("json >> {}" , reservationJsonData);

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
