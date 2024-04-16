package io.hpp.concertreservation.biz.domain.waitqueue.infrastructure;

import io.hpp.concertreservation.biz.domain.reservation.model.PaymentStatus;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IWaitJpaRepository extends JpaRepository<WaitQueue, Long> {

    public Long countByStatus(WaitStatus waitStatus);
    public List<WaitQueue> findByToken(String token);

    public Long countByToken(String token);

}
