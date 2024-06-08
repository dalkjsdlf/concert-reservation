package io.hpp.concertreservation.concert.controller;


import com.google.gson.Gson;
import io.hpp.concertreservation.biz.api.schedule.controller.ScheduleController;
import io.hpp.concertreservation.common.exception.ApiControllerAdvice;
import io.hpp.concertreservation.initdata.InitData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("[Schedule Controller Test]")
@SpringBootTest
public class ScheduleControllerTest {

    private MockMvc mockMvc;

    private final ScheduleController scheduleController;

    private final InitData initData;

    private Long concertId;

    public ScheduleControllerTest(@Autowired ScheduleController scheduleController,
                                  @Autowired InitData initData
    ) {
        this.scheduleController = scheduleController;
        this.initData = initData;
    }

    private Gson gson;

    @BeforeEach
    public void init() {
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController)
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
        assertThat(scheduleController).isNotNull();

    }

    @DisplayName("[성공] concert 스케쥴 조회 공연들 조회")
    @Test()
    public void givenConcertId_whenGetSchedulesByConcertId_thenSchedules() throws Exception {
        // given
        String url = "/api/concerts/" + concertId + "/schedules";
        // when
        ResultActions resultActions = mockMvc.perform(get(url)
                .header(USER_ID_HEADER, 1L)
                .header(TOKEN_HEADER, "1L")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
        //resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].scheduleId").value(1L));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].performDate[0]").value(2023));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].performDate[1]").value(12));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].performDate[2]").value(24));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].performDate[3]").value(17));
    }

    private void initDataInput() {

        concertId  = initData.initDataForConcert().getId();
        initData.initDataForSchedule(concertId);
    }
}
