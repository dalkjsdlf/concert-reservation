package io.hpp.concertreservation.biz.domain.waitqueue.infrastructure;

import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueStoreRepository;
import io.hpp.concertreservation.common.constants.AppConst;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

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
}
