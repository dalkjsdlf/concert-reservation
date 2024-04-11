package io.hpp.concertreservation.biz.domain.schedule.infrastructure;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IScheduleJpaRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByConcertId(Long concertId);
}
