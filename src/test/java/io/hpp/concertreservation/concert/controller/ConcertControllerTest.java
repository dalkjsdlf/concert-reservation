package io.hpp.concertreservation.concert.controller;

import com.google.gson.Gson;
import io.hpp.concertreservation.biz.api.concert.controller.ConcertController;
import io.hpp.concertreservation.biz.domain.concert.service.ConcertService;
import io.hpp.concertreservation.biz.domain.concert.service.IConcertService;
import io.hpp.concertreservation.common.exception.ApiControllerAdvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("[Concert Controller Test]")
@SpringBootTest
public class ConcertControllerTest {

    private MockMvc mockMvc;

    private final ConcertController concertController;
    private final IConcertService concertService;

    public ConcertControllerTest(@Autowired ConcertController concertController,
                                 @Autowired IConcertService concertService) {
        this.concertController = concertController;
        this.concertService = concertService;
    }

    private Gson gson;

    @BeforeEach
    public void init(){
        gson = new Gson();
        mockMvc = MockMvcBuilders.standaloneSetup(concertController)
                .setControllerAdvice(new ApiControllerAdvice())
                .build();
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
        String url = "/api/concerts/";
        // when
        ResultActions resultActions = mockMvc.perform(get(url)
                .header(USER_ID_HEADER, "1L"));

        // then
        resultActions.andExpect(status().isOk());
    }

}
