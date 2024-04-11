package io.hpp.concertreservation.biz.domain.waitqueue.repository;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;

public interface IWaitQueueLoadRepository {


    public Long countAll();
    public Long countByToken(String token);

    public Long countByStatusWork();

    WaitQueue findByToken(String token);
}
