package io.hpp.concertreservation.biz.domain.waitqueue.validationTest;

import io.hpp.concertreservation.biz.domain.waitqueue.component.QueueAppender;
import io.hpp.concertreservation.biz.domain.waitqueue.component.QueueReader;
import io.hpp.concertreservation.biz.domain.waitqueue.component.QueueTokenValidator;
import io.hpp.concertreservation.biz.domain.waitqueue.component.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
@Slf4j
@DisplayName("스케쥴이 수행되면서 대기열을 이동시키는 테스트")
public class ScheduleTest {

    private final QueueTokenValidator  queueTokenValidator;
    private final QueueAppender queueAppender;
    private final QueueReader queueReader;

public ScheduleTest(@Autowired QueueTokenValidator queueTokenValidator,
                    @Autowired QueueAppender queueAppender,
                    @Autowired QueueReader queueReader) {
        this.queueTokenValidator = queueTokenValidator;
        this.queueAppender = queueAppender;
        this.queueReader = queueReader;
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
        Long maxCount = QueueTokenValidator.getMaxWorkingCount();

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


    @DisplayName("[성공] Working 하나 빠지면 하나 추가테스트(은행원식)")
    @Test
    public void givenFullWorkingQueue_whenMoveTokenFromWaitToWorking_thenSuccess(){

        //given
        // wokringqueue 젤 앞에놈 하나 빠짐
        // waitqueue 개수확인

        //when
        //대기열 추가하는 컴포넌트 수행

        //then
        //workingqueue 맥스인지 확인
        //이전 waitqueue개수와 지금 waitqueue개수가 다른지확인
    }
}
