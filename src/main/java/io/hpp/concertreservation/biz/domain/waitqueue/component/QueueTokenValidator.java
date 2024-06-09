package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueLoadRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWorkingQueueLoadRepository;
import io.hpp.concertreservation.common.constants.AppConst;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class QueueTokenValidator {

    private final IWorkingQueueLoadRepository workingQueueLoadRepository;
    private final IWaitQueueLoadRepository waitQueueLoadRepository;

    @Setter
    @Getter
    private static Long maxWorkingCount = AppConst.CONST_MAX_WORKING_COUNT;
    // 인증된?
    // 검증된?
    // 워킹큐에 있다? 이것보다는 좀 더 포괄적이어야 될것같음
    // 왜냐면 이 행위의 궁극적인 목적은 입장을 가능하게 해주는 것이고
    // 입장이 가능하려면 인증된 토큰을 제시는 것이기 때문에 즉 사용자와 가까운 호출
    // 관계이기 때문에 사용자의 행위를 바탕으로 메서드명을 명명해야 한다.
    // 사용자는 입장 티켓을 보여주어

    public boolean isTokenEmpty(String token){
        return StringUtils.isEmpty(token);
    }

    public boolean isValidated(String token){
        Optional<String> optWaitMember = workingQueueLoadRepository.findTokenByToken(token);
        return optWaitMember.isPresent();
    }

    public boolean isWorkingQueueAvailable(){
        Long workingSize = workingQueueLoadRepository.countAllTokens();
        return workingSize.compareTo(maxWorkingCount) < 0;
    }

    public Long getSizeWorkingQueueAvailable(){
        return maxWorkingCount - workingQueueLoadRepository.countAllTokens();
    }

    public boolean isAlreadyInWaitQueue(String token){
        Optional<String> optWaitMember = waitQueueLoadRepository.findTokenByToken(token);
        return optWaitMember.isPresent();
    }
}
