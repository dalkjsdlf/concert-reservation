package io.hpp.concertreservation.biz.domain.waitqueue.infrastructure;

import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueStoreRepository;
import io.hpp.concertreservation.common.constants.AppConst;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class WaitQueueCoreStoreRepository implements IWaitQueueStoreRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public WaitQueueCoreStoreRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void insertToken(String token) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        long newScore = System.currentTimeMillis();
        zSetOperations.add(AppConst.WAIT_QUEUE_KEY,token, newScore);
    }

    @Override
    public void deleteTokenByToken(String token) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.remove(AppConst.WAIT_QUEUE_KEY,token);
    }

    @Override
    public void deleteAllTokens() {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.removeRange(AppConst.WAIT_QUEUE_KEY,0,-1);
    }

    @Override
    public List<String> deleteTokenByRange(int startIdx, int endIdx) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();

        Long size = zSetOperations.count(AppConst.WAIT_QUEUE_KEY, 0, -1);

        if(size == null){
            throw new ReservationException(ReservationErrorResult.UNKNOWN_EXCEPTION);
        }

        if(endIdx == -1){
            endIdx = (int) (size - 1);
        }

        if(size < startIdx + 1 && size > endIdx + 1){
            throw new ReservationException(ReservationErrorResult.WRONG_TOKEN_SIZE);
        }

        Set<String> rangeToken = zSetOperations.range(AppConst.WAIT_QUEUE_KEY,startIdx,endIdx);

        assert rangeToken != null;
        if(rangeToken.isEmpty()){
            throw new ReservationException(ReservationErrorResult.WRONG_TOKEN_SIZE);
        }

        zSetOperations.removeRange(AppConst.WAIT_QUEUE_KEY, startIdx, endIdx);
        return List.copyOf(rangeToken);
    }
}
