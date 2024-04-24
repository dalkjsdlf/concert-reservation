package io.hpp.concertreservation.concert.respository;

import io.hpp.concertreservation.biz.domain.waitqueue.component.TokenGenerator;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitStatus;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueLoadRepository;
import io.hpp.concertreservation.biz.domain.waitqueue.repository.IWaitQueueStoreRepository;
import net.sf.jsqlparser.statement.select.Wait;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("대기열 저장소 테스트")
@SpringBootTest
public class WaitQueueRepositoryTest {

    @Autowired
    @Qualifier("waitQueueCoreLoadRepository")
    private IWaitQueueLoadRepository waitQueueLoadRepository;

    @Autowired
    @Qualifier("waitQueueCoreStoreRepository")
    private IWaitQueueStoreRepository waitQueueStoreRepository;

    @Autowired
    private TokenGenerator tokenGenerator;
    private Long user1Id = 1L;
    private Long user2Id = 2L;
    private Long user3Id = 3L;
    private String tokenForUser1;
    private String tokenForUser2;
    private String tokenForUser3;

    @BeforeEach
    void init(){
        tokenForUser1 = tokenGenerator.generateToken(user1Id,1L);
        tokenForUser2 = tokenGenerator.generateToken(user2Id,2L);
        tokenForUser3 = tokenGenerator.generateToken(user3Id,3L);

        waitQueueStoreRepository.addQueue(WaitQueue.of(tokenForUser1, user1Id, WaitStatus.WORK, LocalDateTime.now()));
        waitQueueStoreRepository.addQueue(WaitQueue.of(tokenForUser2, user2Id, WaitStatus.WORK, LocalDateTime.now()));
        waitQueueStoreRepository.addQueue(WaitQueue.of(tokenForUser3, user3Id, WaitStatus.WAIT, LocalDateTime.now()));

    }
    @DisplayName("Not Null 검사 ")
    @Test()
    public void given_when_thenNotNull(){
        //given


        //when



        //then
        assertThat(waitQueueLoadRepository);
        assertThat(waitQueueStoreRepository);
    }

    @DisplayName("대기열에 특정 토큰 조회")
    @Test()
    public void given_when_then(){
        //given
        List<WaitQueue> waitQueues = waitQueueLoadRepository.findByToken(tokenForUser1);

        //when
        WaitQueue waitQueue = waitQueues.get(0);



        //then
        assertThat(waitQueue).isNotNull();
        assertThat(waitQueue.getUserId()).isEqualTo(user1Id);
        assertThat(waitQueue.getStatus()).isEqualTo(WaitStatus.WORK);
    }
}
