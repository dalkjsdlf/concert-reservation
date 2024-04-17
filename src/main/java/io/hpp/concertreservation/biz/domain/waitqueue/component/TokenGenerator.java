package io.hpp.concertreservation.biz.domain.waitqueue.component;

import org.springframework.stereotype.Component;

import java.util.concurrent.locks.ReentrantLock;

@Component
public class TokenGenerator {
    ReentrantLock reentrantLock = new ReentrantLock();
    public String generateToken(Long userId, Long sortNo){
        reentrantLock.lock();
        StringBuffer sb = new StringBuffer();
        String token = sb.append(userId)
                            .append(sortNo)
                            .append(System.currentTimeMillis())
                            .toString();
        reentrantLock.unlock();
        return token;
    }
}
