package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitMember;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueLoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The type Wait queue reader.
 */
@Component
@RequiredArgsConstructor
public class QueueReader {
    private final IWaitQueueLoadRepository waitQueueLoadRepository;

    /**
     * @Name   : 입장이 가능한 상태를 조회한다.
     * @param  :  token
     * @return : WaitQueue
     */
    public String readWaitQueuePassed(String token){
         Optional<String> optToken = waitQueueLoadRepository.findTokenByToken(token);

        return optToken.orElseGet(optToken::get);

    }

    public WaitMember readWorkingQueueByToken(String token){
        return null;
    }
}
