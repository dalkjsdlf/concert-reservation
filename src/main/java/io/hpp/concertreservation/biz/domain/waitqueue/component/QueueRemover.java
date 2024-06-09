package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueStoreRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWorkingQueueStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueRemover {

    private final IWorkingQueueStoreRepository workingQueueStoreRepository;
    private final IWaitQueueStoreRepository waitQueueStoreRepository;

    public void removeWorkingQueueByRange(int startInx, int endIdx){
        workingQueueStoreRepository.deleteTokenByRange(startInx, endIdx);
    }

    public void removeWaitQueueByRange(int startInx, int endIdx){
        waitQueueStoreRepository.deleteTokenByRange(startInx, endIdx);
    }
}
