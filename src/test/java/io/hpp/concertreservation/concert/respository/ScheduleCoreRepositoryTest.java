package io.hpp.concertreservation.concert.respository;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertStoreRepository;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.schedule.repository.IScheduleLoadRepository;
import io.hpp.concertreservation.biz.domain.schedule.repository.IScheduleStoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("스케쥴 정보 조회 테스트")
@SpringBootTest
@Transactional
@ComponentScan(basePackages = {"io.hpp.concertreservation.biz.domain"})
@Disabled
public class ScheduleCoreRepositoryTest {

    private final IScheduleLoadRepository scheduleLoadRepository;
    private final IScheduleStoreRepository scheduleStoreRepository;
    private final IConcertStoreRepository concertStoreRepository;

    public ScheduleCoreRepositoryTest(@Autowired IScheduleLoadRepository scheduleLoadRepository,
                                      @Autowired IScheduleStoreRepository scheduleStoreRepository,
                                      @Autowired IConcertStoreRepository concertStoreRepository
                                                                                    ) {
        this.scheduleLoadRepository = scheduleLoadRepository;
        this.scheduleStoreRepository = scheduleStoreRepository;
        this.concertStoreRepository = concertStoreRepository;
    }

    private Long phsConcertId = 0L;
    private Long iuConcertId = 0L;

    @BeforeEach
    void init(){
        /**
         * 콘서트 정보 입력하기
         * */
        Concert phsConcert = Concert.of("박효신 콘서트",
                "박효신의 크리스마스 콘서트!",
                "박효신",
                LocalDateTime.of(2023,12,24,0,0,0),
                LocalDateTime.of(2023,12,25,0,0,0));
        Concert iuConcert = Concert.of("아이유 콘서트",
                "아이유의 섬머 파티!",
                "아이유",
                LocalDateTime.of(2024,8,5,0,0,0),
                LocalDateTime.of(2024,8,14,0,0,0));
        Concert savedPhsConcert = concertStoreRepository.saveConcert(phsConcert);
        Concert savedIuConcert  = concertStoreRepository.saveConcert(iuConcert);
        phsConcertId   = savedPhsConcert.getId();
        iuConcertId    = savedIuConcert.getId();
        /**
         * 스케쥴 정보 입력하기
         * */
        Schedule phsSchedule1 = Schedule.of(
                phsConcertId,
                LocalDateTime.of(2023,12,24,17,0,0));
        scheduleStoreRepository.saveSchedule(phsSchedule1);

        Schedule phsSchedule2 = Schedule.of(
                phsConcertId,
                LocalDateTime.of(2023,12,24,21,0,0));
        scheduleStoreRepository.saveSchedule(phsSchedule2);

        Schedule iuSchedule1 = Schedule.of(
                iuConcertId,
                LocalDateTime.of(2024,8,6,17,0,0));
        scheduleStoreRepository.saveSchedule(iuSchedule1);

        Schedule iuSchedule2 = Schedule.of(
                iuConcertId,
                LocalDateTime.of(2024,8,6,21,0,0));
        scheduleStoreRepository.saveSchedule(iuSchedule2);

    }

    @DisplayName("Not null 체크")
    @Test()
    public void given_when_thenNotNull(){
        // given

        // when

        // then
        assertThat(scheduleLoadRepository).isNotNull();
        assertThat(scheduleStoreRepository).isNotNull();
        assertThat(concertStoreRepository).isNotNull();
    }

    @DisplayName("[성공] ConcertId로 스케쥴들 조회하기")
    @Test()
    public void givenConcertId_whenGetAllSchedules_thenSchedules(){
        // given

        // when
        List<Schedule> phsSchedules = scheduleLoadRepository.findSchedulesByConcertId(phsConcertId);

        // then
        assertThat(phsSchedules.size()).isEqualTo(2L);
    }
}
