package io.hpp.concertreservation.biz.domain.schedule.service;

import io.hpp.concertreservation.biz.api.reservation.dto.dto.ScheduleResponseDto;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.schedule.repository.ScheduleReader;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ScheduleService implements IScheduleService{

    private ScheduleReader scheduleReader;

    public ScheduleService(@Autowired ScheduleReader scheduleReader) {
        this.scheduleReader = scheduleReader;
    }

    @Override
    public List<ScheduleResponseDto> getSchedulesByConcertId(Long concertId) {
        List<Schedule> schedules = scheduleReader.readConcertsByConcertId(concertId);

        if(schedules.isEmpty()){
            throw new ReservationException(ReservationErrorResult.NO_SCHEDULE);
        }

        return schedules
                .stream().map(schedule->
                        ScheduleResponseDto
                                .builder()
                                .scheduleId(schedule.getId())
                                .performDate(schedule.getPerformDate())
                                .build()
                ).collect(Collectors.toList());
    }
}
