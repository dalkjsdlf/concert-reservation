package io.hpp.concertreservation.biz.domain.concert.repository;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import io.hpp.concertreservation.initdata.InitData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DisplayName("콘서트 정보 조회 테스트")
@SpringBootTest
@ComponentScan(basePackages = {"io.hpp.concertreservation.biz.domain"})
@Slf4j
@ActiveProfiles("local")
public class ConcertCoreRepositoryTest {

    private final IConcertStoreRepository concertStoreRepository;
    private final IConcertLoadRepository concertLoadRepository;

    private final InitData initData;

    public ConcertCoreRepositoryTest(@Autowired IConcertStoreRepository concertStoreRepository,
                                     @Autowired IConcertLoadRepository concertLoadRepository,
                                     @Autowired InitData initData) {
        this.concertStoreRepository = concertStoreRepository;
        this.concertLoadRepository = concertLoadRepository;
        this.initData = initData;
    }

    @BeforeEach
    void init (){
        //initData.initDataForConcert();
    }

    @DisplayName("[성공] NOT NULL인지 검사하는 테스트")
    @Test()
    public void given_when_thenNotnull(){
        // given

        // when

        // then
        assertThat(concertLoadRepository).isNotNull();
        assertThat(concertStoreRepository).isNotNull();
    }

    @DisplayName("[성공] 콘서트 정보를 모두 조회한다.")
    @Test()
    public void given_whenFindALlConcert_thenConcerts(){
        // given


        // when
        List<Concert> concerts = concertLoadRepository.findAllConcerts();

        // then
        assertThat(concerts.size()).isNotEqualTo(0);
        concerts.forEach(concert->{
           log.info("concert Id [{}] , concert Name [{}]",concert.getId(), concert.getConcertName());
        });
    }

    @DisplayName("[성공] 콘서트 아이디로 콘서트를 조회한다.")
    @Test()
    public void givenId_whenFindById_then(){
        // given
        Long concertId = 1L;

        // when
        Optional<Concert> optResult = concertLoadRepository.findConcert(1L);
        Concert result = optResult.orElseThrow(()->new ReservationException(ReservationErrorResult.NOT_FOUND_CONCERT));
        log.info("result : [{}]",result.getId());
        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(concertId);
    }

}
