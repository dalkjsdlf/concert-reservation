package io.hpp.concertreservation.biz.domain.waitqueue.repository;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitMember;

import java.util.Optional;

public interface IWorkingQueueLoadRepository {

    /**
     * 워킹큐에 토큰이 몇개 있는지 조회
     * */
    public Long countAllTokens();

    /**
     * 워킹큐에 토큰을 찾는다.
     * */
    public Optional<String> findTokenByToken(String token);
}
