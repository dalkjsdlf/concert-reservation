package io.hpp.concertreservation.biz.domain.waitqueue.infrastructure;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitStatus;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueStoreRepository;
import org.springframework.stereotype.Component;

@Component
public class WaitQueueCoreStoreRepository implements IWaitQueueStoreRepository {

    private final IWaitJpaRepository waitJpaRepository;

    public WaitQueueCoreStoreRepository(IWaitJpaRepository waitJpaRepository) {
        this.waitJpaRepository = waitJpaRepository;
    }

    @Override
    public WaitQueue addQueue(WaitQueue waitQueue) {
        return waitJpaRepository.save(waitQueue);
    }

    @Override
    public WaitQueue updateQueueStatus(WaitQueue waitQueue, WaitStatus waitStatus) {
        waitQueue.setStatus(waitStatus);
        return waitJpaRepository.save(waitQueue);
    }

    @Override
    public void deleteQueue(Long waitQueueId) {
        waitJpaRepository.deleteById(waitQueueId);
    }
}
