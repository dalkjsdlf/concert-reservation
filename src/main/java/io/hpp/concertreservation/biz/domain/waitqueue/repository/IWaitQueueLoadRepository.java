package io.hpp.concertreservation.biz.domain.waitqueue.repository;

import java.util.Optional;

public interface IWaitQueueLoadRepository {

    /**
     * 대기큐에 토큰으로 조회한다.
     * */
    public Optional<String> findTokenByToken(String token);
}
