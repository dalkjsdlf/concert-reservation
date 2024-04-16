package io.hpp.concertreservation.biz.api.schedule.usecase;

import io.hpp.concertreservation.biz.api.schedule.dto.ScheduleResponseDto;
import io.hpp.concertreservation.biz.domain.schedule.component.ScheduleReader;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllSchedulesUseCase{
    private final ScheduleReader scheduleReader;

    public List<ScheduleResponseDto> executor(Long concertId){
        List<Schedule> schedules = scheduleReader.readSchedulesByConcertId(concertId);
        return schedules.stream().map(schedule-> ScheduleResponseDto.
                builder().
                scheduleId(schedule.getId()).
                performDate(schedule.getPerformDate()).
                build()).collect(Collectors.toList());
    }
}