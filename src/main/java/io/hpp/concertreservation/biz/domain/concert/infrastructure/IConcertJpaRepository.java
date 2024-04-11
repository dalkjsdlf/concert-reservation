package io.hpp.concertreservation.biz.domain.concert.infrastructure;

import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConcertJpaRepository extends JpaRepository<Concert, Long> {}
