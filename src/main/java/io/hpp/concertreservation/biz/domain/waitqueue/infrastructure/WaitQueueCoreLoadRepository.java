package io.hpp.concertreservation.biz.domain.waitqueue.infrastructure;

import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueLoadRepository;
import io.hpp.concertreservation.common.constants.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class WaitQueueCoreLoadRepository implements IWaitQueueLoadRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public WaitQueueCoreLoadRepository(@Autowired RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Optional<String> findTokenByToken(String token) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Double result = zSetOperations.score(AppConst.WAIT_QUEUE_KEY,token);

        if(result != null)return Optional.of(token);
        else return Optional.empty();
    }

    @Override
    public List<String> findTokensByRange(int startIdx, int endIdx) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<String> rangeToken = zSetOperations.range(AppConst.WAIT_QUEUE_KEY,startIdx,endIdx);
        assert rangeToken != null;
        return List.copyOf(rangeToken);
    }

    @Override
    public Long countAllTokens() {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.zCard(AppConst.WAIT_QUEUE_KEY);
    }
}
