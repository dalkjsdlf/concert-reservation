package io.hpp.concertreservation;

import io.hpp.concertreservation.biz.domain.concert.infrastructure.ConcertCoreRepository;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("콘서트 정보 조회 테스트")
@DataJpaTest
public class ConcertCoreRepositoryTest {

    private ConcertCoreRepository concertCoreRepository;

    public ConcertCoreRepositoryTest(@Autowired ConcertCoreRepository concertCoreRepository) {
        this.concertCoreRepository = concertCoreRepository;
    }

    @BeforeEach
    void init (){
        Concert concert = Concert.of("lee moon sea concert",
                "concert",
                "lee moon sea",
                LocalDateTime.of(2024,3,2,0,0,0),
                LocalDateTime.of(2024,4,2,0,0,0));
        concertCoreRepository.save(concert);
    }

    @DisplayName("")
    @Test()
    public void given_when_thenNotnull(){
        // given

        // when

        // then
        assertThat(concertCoreRepository).isNotNull();
    }

    @DisplayName("")
    @Test()
    public void givenId_whenFindById_then(){
        // given
        Long concertId = 1L;

        // when
        Optional<Concert> optResult = concertCoreRepository.findById(concertId);
        Concert result = optResult.orElseGet(null);
        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(concertId);

    }
}
