package io.hpp.concertreservation.biz.domain.waitqueue.component;

import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class TokenGenerator {

    public static String generateToken(){
        StringBuffer sb = new StringBuffer();
        return sb.append(UUID.randomUUID())
                            .append(System.currentTimeMillis())
                            .toString();
    }

}
