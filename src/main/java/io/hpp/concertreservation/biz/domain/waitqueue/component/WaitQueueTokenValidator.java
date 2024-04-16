package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class WaitQueueTokenValidator {

    private final WaitQueueReader waitQueueReader;

    public boolean valiation(String token){
        WaitQueue workingQueue = waitQueueReader.readWaitQueuePassed(token);

        if(workingQueue == null){
            log.info("인증 실패");
            return false;
        }else{
            log.info("인증 성공");
            return true;
        }
    }
}
