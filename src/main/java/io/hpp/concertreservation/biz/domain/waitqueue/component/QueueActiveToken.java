package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueLoadRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueStoreRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWorkingQueueStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class QueueActiveToken {

    private final QueueTokenValidator queueTokenValidator;
    private final IWaitQueueStoreRepository waitQueueStoreRepository;
    private final IWorkingQueueStoreRepository workingQueueStoreRepository;
    private final IWaitQueueLoadRepository waitQueueLoadRepository;

    @Scheduled(fixedDelay = 5000, initialDelay = 1000)
    public void moveQueue(){
        // 워킹큐에 수용가능한 자리수를 반환한 뒤
        Long availableSize = queueTokenValidator.getSizeWorkingQueueAvailable();
        Long waitSize      = waitQueueLoadRepository.countAllTokens();
        log.debug("Available Size [{}]",availableSize);
        if(availableSize == null || availableSize == 0 || waitSize == null || waitSize == 0){
            return;
        }

        availableSize = availableSize - 1;
        // 그 수 만큼 wait에서 삭제한 뒤 조회한다.
        List<String> tokens =  waitQueueStoreRepository.deleteTokenByRange(0, availableSize.intValue());

        // working에 하나씩 insert
        for (String token : tokens) {
            workingQueueStoreRepository.insertToken(token);
        }
    }
}
