package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitStatus;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueLoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Wait queue reader.
 */
@Component
public class WaitQueueReader {

    private final IWaitQueueLoadRepository waitQueueLoadRepository;

    public WaitQueueReader(@Autowired
                           @Qualifier("waitQueueMapLoadRepository")
                           IWaitQueueLoadRepository waitQueueLoadRepository) {
        this.waitQueueLoadRepository = waitQueueLoadRepository;
    }


    /**
     * @Name   : 입장이 가능한 상태를 조회한다.
     * @param  :  token
     * @return : WaitQueue
     */
    public WaitQueue readWaitQueuePassed(String token){
         List<WaitQueue> waitQueues = waitQueueLoadRepository.findByToken(token);

         if(waitQueues.isEmpty()){
             return null;
         }

        List<WaitQueue> workingQueues = waitQueues.stream().filter(waitQueue->waitQueue.getStatus().equals(WaitStatus.WORK)).collect(Collectors.toList());

        if(workingQueues.size() == 1){
            return workingQueues.get(0);
        }else{
            return null;
        }
    }

    public Long readCountAll(){
        return waitQueueLoadRepository.countAll();
    }
}