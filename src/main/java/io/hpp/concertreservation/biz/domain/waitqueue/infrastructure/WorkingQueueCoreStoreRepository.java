package io.hpp.concertreservation.biz.domain.waitqueue.infrastructure;

import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWorkingQueueStoreRepository;
import io.hpp.concertreservation.common.constants.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@Component
public class WorkingQueueCoreStoreRepository implements IWorkingQueueStoreRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public WorkingQueueCoreStoreRepository(@Autowired RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void insertToken(String token) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        long newScore = System.currentTimeMillis();
        zSetOperations.add(AppConst.ACTIVE_QUEUE_KEY,token, newScore);
    }

    @Override
    public void deleteTokenByToken(String token) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.remove(AppConst.ACTIVE_QUEUE_KEY,token);
    }

    @Override
    public void deleteAllTokens() {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.removeRange(AppConst.ACTIVE_QUEUE_KEY,0,-1);
    }
}
