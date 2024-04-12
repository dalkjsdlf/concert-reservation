package io.hpp.concertreservation.biz.api.schedule.usecase;

import io.hpp.concertreservation.biz.api.schedule.dto.ScheduleResponseDto;
import io.hpp.concertreservation.biz.domain.schedule.component.ScheduleReader;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import org.springframework.stereotype.Component;

@Component
public class GetScheduleUseCase {

    private final ScheduleReader scheduleReader;

    public GetScheduleUseCase(ScheduleReader scheduleReader) {
        this.scheduleReader = scheduleReader;
    }

    public ScheduleResponseDto execute(Long scheduleId){
        Schedule schedule = scheduleReader.readSchedulesById(scheduleId);

        return ScheduleResponseDto.
                builder().
                scheduleId(schedule.getId()).
                performDate(schedule.getPerformDate()).
                build();
    }
}
