package io.hpp.concertreservation.biz.domain.waitqueue.infrastructure;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitStatus;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueLoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WaitQueueCoreLoadRepository implements IWaitQueueLoadRepository {

    private final IWaitJpaRepository waitJpaRepository;

    public WaitQueueCoreLoadRepository(@Autowired IWaitJpaRepository waitJpaRepository) {
        this.waitJpaRepository = waitJpaRepository;
    }

    @Override
    public Long countByStatusWork(){
        return waitJpaRepository.countByStatus(WaitStatus.WORK);
    }
    @Override
    public Long countAll(){
        return waitJpaRepository.count();
    }
    @Override
    public Long countByToken(String token){
        return waitJpaRepository.countByToken(token);
    }
    @Override
    public WaitQueue findByToken(String token){
        Optional<WaitQueue> optWaitQueue = waitJpaRepository.findByToken(token);
        return optWaitQueue.orElseGet(null);
    }
}
