package io.hpp.concertreservation.biz.domain.seat.infrastructure;

import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ISeatJpaRepository extends JpaRepository<Seat, Long> {
    @Query("select s from Seat s where s.scheduleId=:scheduleId")
    @Lock(LockModeType.PESSIMISTIC_READ)
    List<Seat> findByScheduleId(Long scheduleId);

    Optional<Seat> findBySeatNoAndScheduleId(Long seatNo, Long scheduleId);
}
