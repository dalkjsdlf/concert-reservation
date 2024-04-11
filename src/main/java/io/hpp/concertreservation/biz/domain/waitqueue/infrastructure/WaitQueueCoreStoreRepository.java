package io.hpp.concertreservation.biz.domain.waitqueue.infrastructure;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueStoreRepository;
import org.springframework.stereotype.Component;

@Component
public class WaitQueueCoreStoreRepository implements IWaitQueueStoreRepository {

    private final IWaitJpaRepository waitJpaRepository;

    public WaitQueueCoreStoreRepository(IWaitJpaRepository waitJpaRepository) {
        this.waitJpaRepository = waitJpaRepository;
    }

    public void save(WaitQueue waitQueue){
        this.waitJpaRepository.save(waitQueue);
    }
}
