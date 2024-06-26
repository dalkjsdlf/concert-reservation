package io.hpp.concertreservation.biz.api.seat;


import com.google.gson.Gson;
import io.hpp.concertreservation.biz.api.seat.controller.SeatController;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Concert Controller Test]")
@SpringBootTest
@ActiveProfiles("local")
public class SeatControllerTest {

    private final Logger LOGGER = LoggerFactory.getLogger(SeatControllerTest.class);
    private MockMvc mockMvc;

    private final SeatController seatController;


    private final InitData initData;

    private Long concertId;
    private Long scheduleId;
    private Long seatId;

    public SeatControllerTest(@Autowired SeatController seatController,
                              @Autowired InitData initData) {
        this.seatController = seatController;
        this.initData = initData;
    }

    private Gson gson;

    @BeforeEach
    public void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(seatController)
                .setControllerAdvice(new ApiControllerAdvice())
                .build();

        initDataInput();
    }

    @DisplayName("[성공] NULL 체크")
    @Test()
    public void givenNothing_whenNothing_thenNotNull() {
        // given

        // when

        // then
        assertThat(seatController).isNotNull();

    }

    @DisplayName("[성공] concert 스케쥴의 좌석 조회 ")
    @Test()
    public void givenScheduleId_whenGetSeatsByScheduleId_thenSeats() throws Exception {
        // given
        String url = "/api/concerts/" + concertId + "/schedules/" + scheduleId + "/seats";

        // when
        ResultActions resultActions = mockMvc.perform(get(url)
                .header(USER_ID_HEADER, 1L)
                .header(TOKEN_HEADER, "1L")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].seatId").value(seatId));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].seatNo").value(1L));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].seatGrade").value("VIP"));
    }

    private void initDataInput() {

        concertId  = initData.initDataForConcert().getId();
        scheduleId = initData.initDataForSchedule(concertId).getId();
        seatId     = initData.initDataForSeat(scheduleId).get(0).getId();
    }
}
