package io.hpp.concertreservation.biz.domain.schedule.component;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.schedule.repository.IScheduleLoadRepository;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ScheduleReader{

    private final IScheduleLoadRepository scheduleLoadRepository;

    public ScheduleReader(@Autowired IScheduleLoadRepository scheduleLoadRepository) {
        this.scheduleLoadRepository = scheduleLoadRepository;
    }

    public List<Schedule> readSchedulesByConcertId(Long concertId) {
        List<Schedule> schedules = scheduleLoadRepository.findConcertsByConcertId(concertId);

        if(schedules.isEmpty()){
            throw new ReservationException(ReservationErrorResult.NO_SCHEDULE);
        }

        return schedules;
    }

    public Schedule readSchedulesById(Long scheduleId) {
        Optional<Schedule> optSchedule = scheduleLoadRepository.findByScheduleId(scheduleId);
        return optSchedule.orElseThrow(()->new ReservationException(ReservationErrorResult.NO_SCHEDULE));
    }
}