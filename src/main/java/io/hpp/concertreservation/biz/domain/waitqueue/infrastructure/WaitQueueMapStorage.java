package io.hpp.concertreservation.biz.domain.waitqueue.infrastructure;

import io.hpp.concertreservation.biz.domain.waitqueue.component.WaitQueueTokenValidator;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class WaitQueueMapStorage {
    private static Map<String, WaitQueue> waitQueueMap = new HashMap<String, WaitQueue>();
    private static int waitQueueSize = 0;
    public WaitQueueMapStorage(){
        String token = "token1";
        waitQueueMap.put("token1",WaitQueue.of(1L,token, WaitStatus.WORK, LocalDateTime.now()));
    }

    public Map<String, WaitQueue> getWaitQueues(){
        return waitQueueMap;
    }

    public WaitQueue getWaitQueue(String token){
        return waitQueueMap.get(token);
    }

    public void addWaitQueue(String key, WaitQueue waitQueue){
        waitQueueMap.put(key,waitQueue);
    }

    public void renewQueueSize(){
        waitQueueSize = waitQueueMap.size();
    }

    public long getWaitQueueSize(){
        renewQueueSize();
        return (long)waitQueueSize;
    }
}
