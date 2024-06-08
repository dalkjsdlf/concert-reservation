package io.hpp.concertreservation.biz.domain.waitqueue.repository;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitMember;

public interface IWorkingQueueStoreRepository {

    /**
     * 워킹큐에 토큰을 추가한다.
     * */
    public void insertToken(String token);

    /**
     * 워킹큐에 토큰을 삭제한다.
     * */
    public void deleteTokenByToken(String token);

    /**
     * 워킹큐에 모든 토큰을 삭제한다.
     * */
    public void deleteAllTokens();
}

