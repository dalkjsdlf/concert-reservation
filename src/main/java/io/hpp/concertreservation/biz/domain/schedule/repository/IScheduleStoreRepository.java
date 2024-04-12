package io.hpp.concertreservation.biz.domain.schedule.repository;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;

public interface IScheduleStoreRepository {
    void saveSchedule(Schedule schedule);
}
