package io.hpp.concertreservation.concert.respository;

import io.hpp.concertreservation.biz.domain.waitqueue.component.TokenGenerator;
import io.hpp.concertreservation.biz.domain.waitqueue.infrastructure.WaitQueueCoreLoadRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueLoadRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueStoreRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWorkingQueueLoadRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWorkingQueueStoreRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("대기열 저장소 테스트")
@SpringBootTest
@ActiveProfiles("local")
@Slf4j
public class WaitMemberRepositoryTest {

    @Autowired
    private IWaitQueueLoadRepository waitQueueLoadRepository;

    @Autowired
    private IWaitQueueStoreRepository waitQueueStoreRepository;

    @Autowired
    private IWorkingQueueLoadRepository workingQueueLoadRepository;

    @Autowired
    private IWorkingQueueStoreRepository workingQueueStoreRepository;

    private String token;
    @Autowired
    private WaitQueueCoreLoadRepository waitQueueCoreLoadRepository;

    @BeforeEach
    void init(){
        token = TokenGenerator.generateToken();
        workingQueueStoreRepository.insertToken(token);
        waitQueueStoreRepository.insertToken(token);
    }

    @AfterEach
    void after(){
        workingQueueStoreRepository.deleteAllTokens();
        waitQueueStoreRepository.deleteAllTokens();
    }

    @DisplayName("[성공] Not Null 검사 ")
    @Test()
    public void given_when_thenNotNull(){
        //given


        //when

        //then
        assertThat(waitQueueLoadRepository).isNotNull();
        assertThat(waitQueueStoreRepository).isNotNull();
        assertThat(workingQueueLoadRepository).isNotNull();
        assertThat(workingQueueStoreRepository).isNotNull();
    }

    @DisplayName("[성공] Working 대기열에 특정 토큰 조회")
    @Test()
    public void givenToken_whenGetToken_thenToken(){
        //given

        //when
        String foundToken = workingQueueLoadRepository.findTokenByToken(token).orElseThrow(()->new ReservationException(ReservationErrorResult.UNKNOWN_EXCEPTION));

        log.debug("CHECK #1 >>>>>>>>>>>>>>>>>>>> givenToken [{}] foundToken [{}]", token, foundToken);

        //then
        assertThat(foundToken).isEqualTo(token);
    }

    @DisplayName("[성공] Working 대기열에 특정 토큰 입력")
    @Test()
    public void givenToken_whenInsertActiveToken_thenSuccess(){
        //given
        String newToken = TokenGenerator.generateToken();
        //when
        workingQueueStoreRepository.insertToken(newToken);

        //then
        String foundToken = workingQueueLoadRepository.
                findTokenByToken(newToken).
                orElseThrow(()->new ReservationException(ReservationErrorResult.UNKNOWN_EXCEPTION));

        log.debug("CHECK #2 >>>>>>>>>>>>>>>>>>>> newToken [{}] foundToken [{}]", newToken, foundToken);

        assertThat(foundToken).isEqualTo(newToken);
    }

    @DisplayName("[성공] Wait 대기열에 특정 토큰 입력")
    @Test()
    public void givenToken_whenInsertWaitToken_thenSuccess(){
        //given
        String newToken = TokenGenerator.generateToken();
        //when
        waitQueueStoreRepository.insertToken(newToken);
        String foundToken = waitQueueLoadRepository.findTokenByToken(newToken).orElseThrow(()->new ReservationException(ReservationErrorResult.UNKNOWN_EXCEPTION));
        //then
        log.debug("CHECK #3 >>>>>>>>>>>>>>>>>>>> newToken [{}] foundToken [{}]", newToken, foundToken);

        assertThat(foundToken).isEqualTo(newToken);
    }

    @DisplayName("[성공] Working 대기열 선택하여 삭제")
    @Test()
    public void givenToken_whenDeleteWorkingToken_thenSuccess(){
        //given
        Long size = workingQueueLoadRepository.countAllTokens();

        //when
        workingQueueStoreRepository.deleteTokenByToken(token);

        //then
        Long afterSize = workingQueueLoadRepository.countAllTokens();
        log.debug("CHECK #4 >>>>>>>>>>>>>>>>>>>> before size [{}]   after size [{}]", size, afterSize);

        assertThat(size - 1L).isEqualTo(afterSize);
    }

    @DisplayName("[성공] Wait 대기열 선택하여 삭제")
    @Test()
    public void givenToken_whenDeleteWaitToken_thenSuccess(){
        //given

        //when
        waitQueueStoreRepository.deleteTokenByToken(token);


        Optional<String> opt = waitQueueCoreLoadRepository.findTokenByToken(token);

        //then

        assertThat(opt).isEqualTo(Optional.empty());
    }

    @DisplayName("[실패] Working 대기열 조회실패")
    @Test()
    public void givenToken_whenFindWorkingToken_thenFailed(){
        //given
        String newToken = TokenGenerator.generateToken();
        //when
        Optional<String> opt = workingQueueLoadRepository.findTokenByToken(newToken);

        //then
        assertThat(opt).isEqualTo(Optional.empty());
    }

    @DisplayName("[실패] Wait 대기열 조회실패")
    @Test()
    public void givenToken_whenFindWaitToken_thenFailed(){
        //given
        String newToken = TokenGenerator.generateToken();

        //when
        Optional<String> opt = waitQueueLoadRepository.findTokenByToken(newToken);

        //then
        assertThat(opt).isEqualTo(Optional.empty());
    }


}
