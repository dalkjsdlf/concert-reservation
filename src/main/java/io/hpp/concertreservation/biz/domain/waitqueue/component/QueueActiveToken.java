package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QueueActiveToken {

    private final QueueTokenValidator queueTokenValidator;
    private final IWaitQueueStoreRepository waitQueueStoreRepository;

    public void moveQueue(){
        // 워킹큐에 수용가능한 자리수를 반환한 뒤
        Long avaliableSize = queueTokenValidator.getSizeWorkingQueueAvailable();

        // 그 수 만큼 wait에서 삭제한 뒤 조회한다.
        List<String> tokens =  waitQueueStoreRepository.deleteTokenByRange(0, avaliableSize.intValue());

        // working에 하나씩 insert
        for (String token : tokens) {
            waitQueueStoreRepository.insertToken(token);
        }
    }


}
