package io.hpp.concertreservation.biz.api.concert;

import com.google.gson.Gson;
import io.hpp.concertreservation.biz.api.concert.controller.ConcertController;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("[Concert Controller Test]")
@SpringBootTest
@ActiveProfiles("local")
public class ConcertControllerTest {

    private MockMvc mockMvc;

    private final ConcertController concertController;

    private final InitData initData;

    public ConcertControllerTest(@Autowired ConcertController concertController,
                                 @Autowired InitData initData
                                 ) {
        this.concertController = concertController;
        this.initData = initData;
    }

    @BeforeEach
    public void init(){
        Gson gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(concertController)
                .setControllerAdvice(new ApiControllerAdvice())
                .build();

        initDataInput();
    }

    @DisplayName("[성공] NULL 체크")
    @Test()
    public void givenNothing_whenNothing_thenNotNull(){
        // given

        // when

        // then
        assertThat(concertController).isNotNull();
    }

    @DisplayName("[성공] concert 공연들 조회")
    @Test()
    public void given_whenGetConcerts_thenConcert() throws Exception {
        // given
        String url = "/api/concerts";
        // when
        ResultActions resultActions = mockMvc.perform(get(url)
                .header(USER_ID_HEADER, 1L)
                .header(TOKEN_HEADER, "1L")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$[0].concertName").value("박효신 콘서트"));
    }

    @DisplayName("[성공] concert 조회시 토큰 검증")
    @Test()
    public void given_whenCheckToken_thenIsNotNull() throws Exception {
        // given
        String url = "/api/concerts";
        // when
        ResultActions resultActions = mockMvc.perform(get(url)
                .header(USER_ID_HEADER, 1L)
                .header(TOKEN_HEADER, "1L")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
    }

    private void initDataInput(){
        initData.initDataForConcert();
    }
}
