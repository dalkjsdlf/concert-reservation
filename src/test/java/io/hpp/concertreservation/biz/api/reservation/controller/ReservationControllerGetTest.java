package io.hpp.concertreservation.biz.api.reservation.controller;


import com.google.gson.Gson;
import io.hpp.concertreservation.common.exception.ApiControllerAdvice;
import io.hpp.concertreservation.initdata.InitData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Reservation Controller Test")
@SpringBootTest
@ActiveProfiles("local")
public class ReservationControllerGetTest {

    private MockMvc mockMvc;

    private final ReservationController reservationController;


    private final InitData initData;

    private Long concertId;
    private Long scheduleId;
    private Long seatId;
    private Long reservationId;

    private Long userId = 1L;
    private Gson gson;
    public ReservationControllerGetTest(@Autowired ReservationController reservationController,
                                        @Autowired InitData initData) {
        this.reservationController = reservationController;
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

        // when
        ResultActions resultActions = mockMvc.perform(get(url)
                .header(USER_ID_HEADER, 1L)
                .header(TOKEN_HEADER, "1L")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].reservationId").value(reservationId));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].concertName").value("박효신 콘서트"));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].concertName").value("박효신 콘서트"));
    }

    private void initDataInput() {
        concertId     = initData.initDataForConcert().getId();
        scheduleId    = initData.initDataForSchedule(concertId).getId();
        seatId        = initData.initDataForSeat(scheduleId).get(0).getId();
        reservationId = initData.initDataForReserve(userId, scheduleId).getId();
    }
}
