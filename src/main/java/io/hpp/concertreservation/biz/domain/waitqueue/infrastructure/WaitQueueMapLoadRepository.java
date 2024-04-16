package io.hpp.concertreservation.biz.domain.waitqueue.infrastructure;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueLoadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.select.Wait;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class WaitQueueMapLoadRepository implements IWaitQueueLoadRepository {

    private final WaitQueueMapStorage waitQueueMapStorage;

    @Override
    public Long countAll() {
        return null;
    }

    @Override
    public Long countByToken(String token) {
        return null;
    }

    @Override
    public Long countByStatusWork() {
        return null;
    }

    @Override
    public List<WaitQueue> findByToken(String token) {

        Map<String,WaitQueue> queues = waitQueueMapStorage.getWaitQueues();

        log.debug("큐 초기 크기가 몇? [{}]",queues.size());

        Set<String> keyset = queues.keySet();

        List<WaitQueue> resultList = new ArrayList<WaitQueue>();

        for(String key : keyset){
            WaitQueue queue = queues.get(key);
            if(queue.getToken().equals(token)){
                resultList.add(queue);
            }
        }

        return resultList;
    }
}
