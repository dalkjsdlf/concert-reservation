package io.hpp.concertreservation.concert.respository;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertLoadRepository;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertStoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("콘서트 정보 조회 테스트")
@SpringBootTest
@ComponentScan(basePackages = {"io.hpp.concertreservation.biz.domain"})
public class ConcertCoreRepositoryTest {

    private IConcertStoreRepository concertStoreRepository;
    private IConcertLoadRepository concertLoadRepository;

    public ConcertCoreRepositoryTest(@Autowired IConcertStoreRepository concertStoreRepository,
                                     @Autowired IConcertLoadRepository concertLoadRepository) {
        this.concertStoreRepository = concertStoreRepository;
        this.concertLoadRepository = concertLoadRepository;
    }

    @BeforeEach
    void init (){
        Concert phsConcert = Concert.of("박효신 콘서트",
                "박효신의 크리스마스 콘서트!",
                "박효신",
                LocalDateTime.of(2024,3,2,0,0,0),
                LocalDateTime.of(2024,5,2,0,0,0));
        concertStoreRepository.saveConcert(phsConcert);
    }

    @DisplayName("")
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
        assertThat(concerts.size()).isEqualTo(1);
    }

    @DisplayName("[성공] 콘서트 아이디로 콘서트를 조회한다.")
    @Test()
    public void givenId_whenFindById_then(){
        // given
        Long concertId = 1L;

        // when
        Optional<Concert> optResult = concertLoadRepository.findConcert(concertId);
        Concert result = optResult.orElseGet(null);
        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(concertId);
    }
}
