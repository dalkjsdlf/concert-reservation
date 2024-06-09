package io.hpp.concertreservation.biz.domain.waitqueue.infrastructure;

import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWorkingQueueLoadRepository;
import io.hpp.concertreservation.common.constants.AppConst;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WorkingQueueCoreLoadRepository implements IWorkingQueueLoadRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public WorkingQueueCoreLoadRepository(@Autowired RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long countAllTokens() {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.zCard(AppConst.ACTIVE_QUEUE_KEY);
    }

    @Override
    public Optional<String> findTokenByToken(String token) {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Double result = zSetOperations.score(AppConst.ACTIVE_QUEUE_KEY,token);

        if(result != null) return Optional.of(token);
        else return Optional.empty();
    }
}
