package io.hpp.concertreservation.biz.domain.seat.service;

import io.hpp.concertreservation.biz.api.seat.dto.SeatRequestDto;
import io.hpp.concertreservation.biz.api.seat.dto.SeatResponseDto;
import io.hpp.concertreservation.biz.domain.schedule.repository.ScheduleReader;
import io.hpp.concertreservation.biz.domain.schedule.service.ScheduleService;
import io.hpp.concertreservation.biz.domain.seat.enumclass.SeatGrade;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.repository.SeatReader;
import io.hpp.concertreservation.biz.domain.seat.repository.SeatWriter;
import io.hpp.concertreservation.common.exception.ReservationErrorResult;
import io.hpp.concertreservation.common.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService implements ISeatService{

    private final SeatReader seatReader;
    private final SeatWriter seatWriter;

    private final ScheduleReader scheduleReader;

    public SeatService(@Autowired SeatReader seatReader,
                       @Autowired SeatWriter seatWriter,
                       @Autowired ScheduleReader scheduleReader) {
        this.seatReader = seatReader;
        this.seatWriter = seatWriter;
        this.scheduleReader = scheduleReader;
    }

    @Override
    public List<SeatResponseDto> getSeatsOnSchedule(Long scheduleId) {

        List<Seat> seats = seatReader.readSeatsByScheduleId(scheduleId);

        if(seats.isEmpty()){
            throw new ReservationException(ReservationErrorResult.NO_SEATS);
        }

        return seats.stream()
                .map(seat-> SeatResponseDto
                        .builder()
                        .seatId(seat.getId())
                        .seatNo(seat.getSeatNo())
                        .reserveId(seat.getReserveId())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public Seat registerSeatOnSchedule(SeatRequestDto seatRequestDto) {

        Long price      = seatRequestDto.getPrice();
        Long scheduleId = seatRequestDto.getScheduleId();
        Long seatNo     = seatRequestDto.getSeatNo();

        if(price < 0){
            throw new ReservationException(ReservationErrorResult.NEGATIVE_PRICE);
        }

        if(scheduleReader.readByScheduleId(scheduleId).isPresent()){
            throw new ReservationException(ReservationErrorResult.NO_SCHEDULE);
        }

        /*
        * desc : 해당 시간대에 좌석이 이미 존재하는지 확인
        * */
        Optional<Seat> optSeat = seatReader.readSeatBySeatNoAndScheduleId(seatNo, scheduleId);
        if(optSeat.isPresent()){
            throw new ReservationException(ReservationErrorResult.ALREADY_SEAT_REGISTERED);
        }

        Seat newSeat = Seat.of(scheduleId,
                seatNo,
                SeatGrade.A,
                price,
                null);

        return seatWriter.writeSeat(newSeat);
    }
}
