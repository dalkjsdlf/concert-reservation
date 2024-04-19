package io.hpp.concertreservation.biz.domain.waitqueue.repository;

import io.hpp.concertreservation.biz.domain.waitqueue.infrastructure.WaitQueueCoreLoadRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitStatus;

public interface IWaitQueueStoreRepository {

    public WaitQueue addQueue(WaitQueue waitQueue);
    public WaitQueue updateQueueStatus(WaitQueue waitQueue, WaitStatus waitStatus);
    public void deleteQueue(Long waitQueueId);
}
