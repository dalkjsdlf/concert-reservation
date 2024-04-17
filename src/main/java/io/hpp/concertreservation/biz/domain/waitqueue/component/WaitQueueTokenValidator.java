package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class WaitQueueTokenValidator {

    private final WaitQueueReader waitQueueReader;

    private final Long CONST_LIMIT = 100L;
    public boolean valiation(String token){
        WaitQueue workingQueue = waitQueueReader.readWaitQueuePassed(token);
        return Optional.of(workingQueue).isPresent();
    }
}
