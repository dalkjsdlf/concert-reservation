package io.hpp.concertreservation.biz.domain.concert.infrastructure;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertInfoCoreRepository extends JpaRepository<Concert, Long> {

}
