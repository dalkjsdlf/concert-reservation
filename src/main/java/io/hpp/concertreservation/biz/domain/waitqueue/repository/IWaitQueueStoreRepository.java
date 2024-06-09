package io.hpp.concertreservation.biz.domain.waitqueue.repository;

import java.util.List;

public interface IWaitQueueStoreRepository {

    /**
     * 대기큐에 토큰을 추가한다.
     */
    public void insertToken(String token);

    /**
     * 대기큐에 토큰을 삭제한다.
     */
    public void deleteTokenByToken(String token);

    /**
     * 대기큐의 모든 토큰을 삭제한다.
     */
    public void deleteAllTokens();

    public List<String> deleteTokenByRange(int startIdx, int endIdx);
}
