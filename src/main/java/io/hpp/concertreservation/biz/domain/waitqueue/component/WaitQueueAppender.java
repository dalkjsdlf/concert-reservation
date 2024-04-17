package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueLoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WaitQueueAppender {

    private final WaitQueueCheckAvailable waitQueueCheckAvailable;

    private final TokenGenerator tokenGenerator;

    private final WaitQueueReader waitQueueReader;

    public void addWaitQueue(Long userId){
        Long seqNo = waitQueueReader.readCountAll();
        seqNo++;

        //말로하자 말로

    }

}
