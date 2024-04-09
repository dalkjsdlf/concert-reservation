package io.hpp.concertreservation.biz.domain.concert.infrastructure;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertCoreRepository extends JpaRepository<Concert, Long> {

}
