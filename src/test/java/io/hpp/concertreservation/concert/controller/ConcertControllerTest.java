package io.hpp.concertreservation.concert.controller;

import com.google.gson.Gson;
import io.hpp.concertreservation.biz.api.concert.controller.ConcertController;
import io.hpp.concertreservation.biz.domain.concert.service.ConcertService;
import io.hpp.concertreservation.common.exception.ApiControllerAdvice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("[Concert Controller Test]")
public class ConcertControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ConcertController concertController;
    @Mock
    private ConcertService concertService;

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

}
