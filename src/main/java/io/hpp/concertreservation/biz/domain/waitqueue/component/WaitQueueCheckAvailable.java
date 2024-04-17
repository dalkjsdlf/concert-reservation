package io.hpp.concertreservation.biz.domain.waitqueue.component;

import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class WaitQueueCheckAvailable {

    public final WaitQueueReader waitQueueReader;

    public boolean isAvailableEnter(Long limit){
        Optional.of(limit).orElseThrow(()->new ReservationException(ReservationErrorResult.VALUE_IS_EMPTY));
        return (limit.compareTo(waitQueueReader.readCountAll()) > 0);
    }
}
