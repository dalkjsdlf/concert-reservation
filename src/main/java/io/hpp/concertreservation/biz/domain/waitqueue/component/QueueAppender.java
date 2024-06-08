package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitMember;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueStoreRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWorkingQueueStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The type Queue appender.
 */
@Component
@RequiredArgsConstructor
public class QueueAppender {

    //private final QueueCheckAvailable waitQueueCheckAvailable;

     //  working loader
       // count
       // count < max?
     // working store
         // add

     // waiting loader
       // not found token
     // waiting store
       // add

    private final IWorkingQueueStoreRepository workingQueueStoreRepository;
    private final IWaitQueueStoreRepository waitQueueCoreStoreRepository;

    private final QueueTokenValidator queueTokenValidator;
    /**
     * 작업중 큐와 대기큐를 추가하는 로직
     * ==========================================
     * 1. 워킹 큐 추가시도
     * 2. 워킹 큐 추가 실패 시, 대기 큐 추가시도
     * ==========================================
     * @param token 검증할 토큰
     */
    public boolean addQueue(String token){

        /*
        * 워킹 큐 추가
        * 1. 워킹 큐 추가 성공시 인증 완료 처리(true 반환)
        * 2. 워킹 큐 추가 실패시 대기 큐 추가 시도
        * */
        if(addWorkingQueue(token)){
           return true;
        }

        /*
         * 대기 큐 추가
         * 1. 이미 대기큐에 있으면 반환
         * 2. 이미 대기큐에 없으면 대기큐에 추가
         * */
        addWaitQueue(token);

        /*
        * false 반환시 token 인증 통과 안됨
        * */
        return false;
    }


    /**
     * Add working queue.
     *
     * @param token the token
     */
    public boolean addWorkingQueue(String token){
        if(queueTokenValidator.isWorkingQueueAvailable()){
            workingQueueStoreRepository.insertToken(token);
            return true;
        }
        return false;
    }

    public void addWaitQueue(String token){
        /**
         * 이미 대기큐 있지 않다면 대기큐에 추가한다.
         * */
        if(!queueTokenValidator.isAlreadyInWaitQueue(token)) {
            waitQueueCoreStoreRepository.insertToken(token);
        }
    }

}
