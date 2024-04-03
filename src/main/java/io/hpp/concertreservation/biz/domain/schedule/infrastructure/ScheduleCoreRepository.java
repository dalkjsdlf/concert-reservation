package io.hpp.concertreservation.biz.domain.schedule.infrastructure;

import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleCoreRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByConcertId(Long concertId);
}
