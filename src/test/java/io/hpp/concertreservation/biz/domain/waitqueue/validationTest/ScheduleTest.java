package io.hpp.concertreservation.biz.domain.waitqueue.validationTest;

import io.hpp.concertreservation.biz.domain.waitqueue.component.*;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueLoadRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWorkingQueueLoadRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("local")
@Slf4j
@DisplayName("스케쥴이 수행되면서 대기열을 이동시키는 테스트")
public class ScheduleTest {

    private final QueueTokenValidator  queueTokenValidator;
    private final QueueAppender queueAppender;
    private final QueueRemover queueRemover;
    private final QueueActiveToken queueActiveToken;
    private final IWaitQueueLoadRepository waitQueueLoadRepository;
    private final IWorkingQueueLoadRepository workingQueueLoadRepository;

    @Autowired
    public ScheduleTest(QueueTokenValidator queueTokenValidator,
                        QueueAppender queueAppender,
                        QueueRemover queueRemover,
                        QueueActiveToken queueActiveToken,
                        IWaitQueueLoadRepository waitQueueLoadRepository,
                        IWorkingQueueLoadRepository workingQueueLoadRepository) {
        this.queueTokenValidator = queueTokenValidator;
        this.queueAppender = queueAppender;
        this.queueRemover = queueRemover;
        this.queueActiveToken = queueActiveToken;
        this.waitQueueLoadRepository = waitQueueLoadRepository;
        this.workingQueueLoadRepository = workingQueueLoadRepository;
    }

    /*
    * 요청이 100개 들어와
    * working 큐가 꽉 차있는 상태야 100개되
    * 근데 이상황에서 요청이 50개 정도 더 들어와
    * 50개는 대기타겠지?
    * 그 상황에서
    * working 하나 빠졌어
    * 그 때!!!!
    * 여기서부터 스케쥴러가 하는 일이야
    * working 큐가 꽉 찼는지 검사하구
    * 자리 비면
    * wait큐 앞에서 부터 채워넣어
    * 은행원식의 단점은 working큐가 오랬동안 차지하고 있으면 대기열의 대기 시간이 너무너무 길어져
    * 그래서 5분이면 5분! 10분이면 10분 시간을 정해놓고 일정 개수만큼 working큐의 수용 공간을 늘려
    * 이 방법의 큰 장점은 나는 현재 대기열에 있어도 일정 시간이 지나면 working큐로 갈 수 있다는 보장이 있어
    * 즉 대기열의 역할은 fix된 working으로 제한을 두는 역할이 아닌
    * active 요청자의 유량조절 역할을 한다고 볼 수 있지 이런식으로 트래픽을 제어하는 방법이야
    *
    * 은행원식 : fix된 wokring인원이 보장되어 빠진 인원수만큼 순차적으로 들여보내는 방식
    *     * 장점
    *        fix된 인원만 시스템을 이용할 수 있게 제어가 가능하여 부하를 확실히 막을 수 있다.
    *     * 단점
    *        대기인원수의 유량 속도가 더 빨라지게 되면 대기 인원이 순서가 오기전까지 한없이 기다리게 된다.
    *
    * 놀이공원식 : working queue를 특정 시간이 지나면 늘려나가는 방식
    *     * 장점 : 대기열에서 대기시간이 길어지는 상황을 막을 수 있다.
    *     * 단점 : working queue 증가 시간과 양조절에 실패하게 되면 부하상황에 맞닥들일 수 있다.
    * */
    @BeforeEach
    public void before() {
        queueRemover.removeWorkingQueueByRange(0,-1);
        queueRemover.removeWaitQueueByRange(0,-1);

        Long maxCount = QueueTokenValidator.getMaxWorkingCount();
        log.debug("#1 CHECK : GetMaxworkingCount [{}]", maxCount);
        // 100명 Working 큐에 넣기
        for (int i = 0; i < maxCount; i++) {
            queueAppender.addWorkingQueue(TokenGenerator.generateToken());
        }

        //50명 대기큐에 넣기
        for (int i = 0; i < 50; i++) {
            queueAppender.addWaitQueue(TokenGenerator.generateToken());
        }

        //workingqueue 에 100명 입성
        //waitqueue에 50명 입성
    }

    @DisplayName("[ 성공 ] working queue에 token이 100개 있는지 확인한다.")
    @Test
    public void givenNothing_whenCheck100OfTokensExists_thenHas100(){
        //given

        //when
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Long size = workingQueueLoadRepository.countAllTokens();

        stopWatch.stop();

        stopWatch.prettyPrint(TimeUnit.MILLISECONDS);
        stopWatch.getTotalTimeMillis();
        //then
        // working의 사이즈 입니다.
        log.debug(" #2 CHECK : Working queue size [{}]", size);

        assertThat(size).isEqualTo(100);

    }

    @DisplayName("[성공] Working 하나 빠지면 하나 추가테스트(은행원식)")
    @Test
    public void givenFullWorkingQueue_whenMoveTokenFromWaitToWorking_thenSuccess(){

        //given
        // wokringqueue 젤 앞에놈 두 놈 빠짐
        // waitqueue 개수확인

        int startIdx = 0;
        int endIdx = 1;

        Long beforeAvailableSize = queueTokenValidator.getSizeWorkingQueueAvailable();
        Long beforeWaitSize      = waitQueueLoadRepository.countAllTokens();

        log.debug("#2 CHECK >>> Before Size workingQueue 개수 [{}] waitQueue개수 [{}]", workingQueueLoadRepository.countAllTokens(), waitQueueLoadRepository.countAllTokens());
        log.debug("#3 CHECK >>> Before Size 진입 가능한 workingQueue 개수 [{}] waitQueue개수 [{}]", beforeAvailableSize, waitQueueLoadRepository.countAllTokens());

        int size = endIdx - startIdx + 1;
        queueRemover.removeWorkingQueueByRange(startIdx, endIdx);

        //when
        log.debug("#4 CHECK >>> 대기열 이동 컴포넌트 수행");
        //대기열 추가하는 컴포넌트 수행
        queueActiveToken.moveQueue();

        Long afterAvailableSize = queueTokenValidator.getSizeWorkingQueueAvailable();

        //then
        log.debug("#5 CHECK >>> After Size 진입 가능한 workingQueue 개수 [{}] waitQueue개수 [{}]", afterAvailableSize, waitQueueLoadRepository.countAllTokens());
        //workingqueue 맥스인지 확인
        //이전 waitqueue개수와 지금 waitqueue개수가 다른지확인

        assertThat(queueTokenValidator.isWorkingQueueAvailable()).isEqualTo(false);
        assertThat(waitQueueLoadRepository.countAllTokens()).isEqualTo(beforeWaitSize - size);
    }

    @DisplayName("[성공] 대기큐와 워키큐에 아무것도 없을 때 아무 동작도 하지 않는지 확인 테스트")
    @Test
    public void givenNothing_whenSchedule_thenNothingHappen(){
        //given
        queueRemover.removeWorkingQueueByRange(0,-1);
        queueRemover.removeWaitQueueByRange(0,-1);

        //when
        queueActiveToken.moveQueue();

        //then

        assertThat(waitQueueLoadRepository.countAllTokens()).isEqualTo(0);
        assertThat(workingQueueLoadRepository.countAllTokens()).isEqualTo(0);

    }

    @DisplayName("[성공] 워킹큐에는 토큰이 존재하지만 대기큐에는 아무 토큰값도 없을 때 아무 동작도 하지 않는지 확인")
    @Test
    public void givenNothingWait_whenSchedule_thenNothingHappen(){
        //given
        queueRemover.removeWorkingQueueByRange(0,49);
        queueRemover.removeWaitQueueByRange(0,-1);

        //when
        queueActiveToken.moveQueue();

        //then

        assertThat(waitQueueLoadRepository.countAllTokens()).isEqualTo(0);
        assertThat(workingQueueLoadRepository.countAllTokens()).isEqualTo(50);

    }

    @AfterEach
    public void after() {
        queueRemover.removeWorkingQueueByRange(0,-1);
        queueRemover.removeWaitQueueByRange(0,-1);
    }
}
