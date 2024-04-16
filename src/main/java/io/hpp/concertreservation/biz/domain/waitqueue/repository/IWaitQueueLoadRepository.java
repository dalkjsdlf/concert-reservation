package io.hpp.concertreservation.biz.domain.waitqueue.repository;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitStatus;

import java.util.List;
import java.util.Optional;

public interface IWaitQueueLoadRepository {


    public Long countAll();
    public Long countByToken(String token);

    public Long countByStatusWork();

    List<WaitQueue> findByToken(String token);
}
