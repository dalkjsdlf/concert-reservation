package io.hpp.concertreservation.common.constants;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AppConst {

    public static final Long CONST_WORKING_QUEUE_TIMEOUT_SECOND = 300L;
    public static final Long CONST_RESERVATION_TIMEOUT_MINUTES = 300L;
    public static final Long CONST_MAX_WORKING_COUNT = 100L;
    public static final String ACTIVE_QUEUE_KEY = "ACTIVE_QUEUE_KEY";
    public static final String WAIT_QUEUE_KEY = "WAIT_QUEUE_KEY";
}
