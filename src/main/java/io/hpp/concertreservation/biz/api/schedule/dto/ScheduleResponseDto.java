package io.hpp.concertreservation.biz.api.schedule.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@Builder
public class ScheduleResponseDto {

    private final Long scheduleId;
    private final LocalDateTime performDate;
}
