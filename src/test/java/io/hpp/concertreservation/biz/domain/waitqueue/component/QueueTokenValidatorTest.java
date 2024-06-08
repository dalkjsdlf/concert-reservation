package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueStoreRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWorkingQueueStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
@DisplayName("대기열 검증관련 테스트")
class QueueTokenValidatorTest {

    private final QueueTokenValidator queueTokenValidator;
    private final IWaitQueueStoreRepository waitQueueStoreRepository;

    private final IWorkingQueueStoreRepository workingQueueStoreRepository;

    public QueueTokenValidatorTest(@Autowired QueueTokenValidator queueTokenValidator,
                                   @Autowired IWaitQueueStoreRepository waitQueueStoreRepository,
                                   @Autowired IWorkingQueueStoreRepository workingQueueStoreRepository
                                   ) {
        this.queueTokenValidator = queueTokenValidator;
        this.waitQueueStoreRepository = waitQueueStoreRepository;
        this.workingQueueStoreRepository = workingQueueStoreRepository;
    }

    private String token;

    @BeforeEach
    void setUp() {
        token = TokenGenerator.generateToken();
        waitQueueStoreRepository.insertToken(token);
    }

    @DisplayName("[성공] Not Null 체크")
    @Test
    public void given_whenCheckNotnull_then(){
        //given

        //when

        //then
        assertThat(queueTokenValidator).isNotNull();
    }

    @DisplayName("[성공] Working 큐가 추가 가능한 상황인지 검증")
    @Test
    public void givenNothing_whenCheckWorkingQueueAvailable_thenTrue(){
        //given

        //when
        boolean result = queueTokenValidator.isWorkingQueueAvailable();

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("[성공] Wait 큐에 이미 토큰이 존재하는지 검사")
    @Test
    public void givenNothing_whenCheckTokenAlreadyExistsInWaitQueue_thenTrue(){
        //given

        //when
        boolean result = queueTokenValidator.isAlreadyInWaitQueue(token);

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("[실패] Working 큐가 추가가 불가능한 상황")
    @Test
    public void givenInTokensUntilMaxQueueSize_whenCheckWorkingQueueAvailable_thenFalse(){
        //given
        Long maxCount = QueueTokenValidator.getMaxWorkingCount();

        log.debug("#0 CHECK maxCount [{}]", maxCount);

        for (int i = 0; i < maxCount; i++) {
            log.debug("============  #{} CHECK token [{}] insert ============  ", i + 1,token);
            workingQueueStoreRepository.insertToken(TokenGenerator.generateToken());
        }

        //when
        boolean result = queueTokenValidator.isWorkingQueueAvailable();

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("[성공] Working 큐가 추가불가능한 상황에서 큐 크기를 늘려 가능 - 놀이공원식 테스트")
    @Test
    public void givenInTokensUntilMaxQueueSizeAndScaleUp_whenCheckWorkingQueueAvailable_thenFalse(){
        //given
        Long maxCount = QueueTokenValidator.getMaxWorkingCount();

        log.debug("#0 CHECK maxCount [{}]", maxCount);

        for (int i = 0; i < maxCount; i++) {
            log.debug("============  #{} CHECK token [{}] insert ============  ", i + 1,token);
            workingQueueStoreRepository.insertToken(TokenGenerator.generateToken());
        }

        //when
        boolean result = queueTokenValidator.isWorkingQueueAvailable();

        //then
        assertThat(result).isFalse();

        QueueTokenValidator.setMaxWorkingCount(maxCount + 100L);

        log.debug("#111 CHECK maxCount [{}]", QueueTokenValidator.getMaxWorkingCount());

        boolean result2 = queueTokenValidator.isWorkingQueueAvailable();

        assertThat(result2).isTrue();
    }

    @AfterEach
    void tearDown(){
        workingQueueStoreRepository.deleteAllTokens();
        waitQueueStoreRepository.deleteAllTokens();
    }
}