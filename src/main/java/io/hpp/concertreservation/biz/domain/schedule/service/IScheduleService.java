package io.hpp.concertreservation.biz.domain.schedule.service;

import io.hpp.concertreservation.biz.api.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface IScheduleService {

    List<ScheduleResponseDto> getSchedulesByConcertId(Long concertId);
}
