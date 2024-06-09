package io.hpp.concertreservation.biz.api.reservation.usecase;

import io.hpp.concertreservation.biz.api.payment.dto.PaymentRequestDto;
import io.hpp.concertreservation.biz.api.payment.usecase.GetAllPaymentUseCase;
import io.hpp.concertreservation.biz.api.payment.usecase.PayReservation;
import io.hpp.concertreservation.biz.api.reservation.dto.ReservationRequestDto;
import io.hpp.concertreservation.biz.domain.concert.component.ConcertReader;
import io.hpp.concertreservation.biz.domain.concert.model.Concert;
import io.hpp.concertreservation.biz.domain.concert.repository.IConcertStoreRepository;
import io.hpp.concertreservation.biz.domain.payment.component.PaymentModifier;
import io.hpp.concertreservation.biz.domain.payment.component.PaymentReader;
import io.hpp.concertreservation.biz.domain.payment.model.Payment;
import io.hpp.concertreservation.biz.domain.paymoney.component.PayMoneyModifier;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationModifier;
import io.hpp.concertreservation.biz.domain.reservation.component.ReservationReader;
import io.hpp.concertreservation.biz.domain.reservation.model.Reservation;
import io.hpp.concertreservation.biz.domain.schedule.component.ScheduleReader;
import io.hpp.concertreservation.biz.domain.schedule.model.Schedule;
import io.hpp.concertreservation.biz.domain.schedule.repository.IScheduleLoadRepository;
import io.hpp.concertreservation.biz.domain.schedule.repository.IScheduleStoreRepository;
import io.hpp.concertreservation.biz.domain.seat.component.SeatReader;
import io.hpp.concertreservation.biz.domain.seat.model.Seat;
import io.hpp.concertreservation.biz.domain.seat.model.SeatGrade;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatLoadRepository;
import io.hpp.concertreservation.biz.domain.seat.repository.ISeatStoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("콘서트를 예약 및 결제 유스케이스 테스트")
@SpringBootTest
@ActiveProfiles("local")
public class ReserveConcertAndPaymentUseCaseTest {

    private ReserveConcertUseCase reserveConcertUseCase;
    private final IScheduleLoadRepository scheduleLoadRepository;
    private final IScheduleStoreRepository scheduleStoreRepository;
    private final IConcertStoreRepository concertStoreRepository;
    private final ISeatLoadRepository seatLoadRepository;
    private final ISeatStoreRepository seatStoreRepository;

    private final ConcertReader concertReader;

    private final ScheduleReader scheduleReader;

    private final SeatReader seatReader;

    private final ReservationReader reservationReader;

    private final ReservationModifier reservationModifier;

    private final PayMoneyModifier payMoneyModifier;
    private final PaymentReader paymentReader;

    private final PaymentModifier paymentModifier;

    private final PayReservation payReservation;
    
    private final GetAllPaymentUseCase getAllPaymentUseCase;


    private Long phsConcertScheduleId = 0L;
    private Long phsConcertSecondScheduleId = 0L;

    private Long phsConcertSeatId;

    private Long userId = 1L;

    @Autowired
    public ReserveConcertAndPaymentUseCaseTest( ReserveConcertUseCase reserveConcertUseCase,
                                                IScheduleLoadRepository scheduleLoadRepository,
                                                IScheduleStoreRepository scheduleStoreRepository,
                                                IConcertStoreRepository concertStoreRepository,
                                                ISeatLoadRepository seatLoadRepository,
                                                ISeatStoreRepository seatStoreRepository,
                                                ConcertReader concertReader,
                                                ScheduleReader scheduleReader,
                                                SeatReader seatReader,
                                                ReservationReader reservationReader,
                                                ReservationModifier reservationModifier,
                                                PayMoneyModifier payMoneyModifier,
                                                PaymentReader paymentReader,
                                                PaymentModifier paymentModifier,
                                                PayReservation payReservation,
                                                GetAllPaymentUseCase getAllPaymentUseCase
    ) {
        this.reserveConcertUseCase = reserveConcertUseCase;
        this.scheduleLoadRepository = scheduleLoadRepository;
        this.scheduleStoreRepository = scheduleStoreRepository;
        this.concertStoreRepository = concertStoreRepository;
        this.seatLoadRepository = seatLoadRepository;
        this.seatStoreRepository = seatStoreRepository;
        this.concertReader = concertReader;
        this.scheduleReader = scheduleReader;
        this.seatReader = seatReader;
        this.reservationReader = reservationReader;
        this.reservationModifier = reservationModifier;
        this.payMoneyModifier = payMoneyModifier;
        this.paymentReader = paymentReader;
        this.paymentModifier = paymentModifier;
        this.payReservation = payReservation;
        this.getAllPaymentUseCase = getAllPaymentUseCase;
    }

    @DisplayName("박효신 콘서트 정보 입력, 스케쥴 2023-12-24 17시, 21시 공연 스케쥴 입력, 17시 3석, 21시 2석 입력")
    @BeforeEach
    public void init() {
        /**
         * 콘서트 정보 입력하기
         * */
        Concert phsConcert = Concert.of("박효신 콘서트",
                "박효신의 크리스마스 콘서트!",
                "박효신",
                LocalDateTime.of(2023, 12, 24, 0, 0, 0),
                LocalDateTime.of(2023, 12, 25, 0, 0, 0));

        Concert savedPhsConcert = concertStoreRepository.saveConcert(phsConcert);
        Long phsConcertId = savedPhsConcert.getId();

        /**
         * 스케쥴 정보 입력하기
         * */
        Schedule phsSchedule1 = Schedule.of(
                phsConcertId,
                LocalDateTime.of(2023, 12, 24, 17, 0, 0));
        scheduleStoreRepository.saveSchedule(phsSchedule1);

        Schedule phsSchedule2 = Schedule.of(
                phsConcertId,
                LocalDateTime.of(2023, 12, 24, 21, 0, 0));
        scheduleStoreRepository.saveSchedule(phsSchedule2);
        phsConcertScheduleId = phsSchedule1.getId();
        phsConcertSecondScheduleId = phsSchedule2.getId();
        /**
         * 좌석 정보 입력하기 1 ~ 50
         * 총 5개 입력하기
         * 박효신 콘서트 17시 공연 3석, 21시 공연 2석
         * */
        Seat seat1 = Seat.of(phsConcertScheduleId, 1L, SeatGrade.VIP, 240000L, -1L);
        Seat seat2 = Seat.of(phsConcertScheduleId, 2L, SeatGrade.R, 120000L, -1L);
        Seat seat3 = Seat.of(phsConcertScheduleId, 3L, SeatGrade.R, 120000L, -1L);
        Seat seat4 = Seat.of(phsConcertSecondScheduleId, 1L, SeatGrade.VIP, 250000L, -1L);
        Seat seat5 = Seat.of(phsConcertSecondScheduleId, 2L, SeatGrade.R, 120000L, -1L);

        Seat savedSeat1 = seatStoreRepository.saveSeat(seat1);
        seatStoreRepository.saveSeat(seat2);
        seatStoreRepository.saveSeat(seat3);
        seatStoreRepository.saveSeat(seat4);
        seatStoreRepository.saveSeat(seat5);

        phsConcertSeatId = savedSeat1.getId();

        /**
         * 돈 백만원 충전하기
         * */
        payMoneyModifier.charge(userId,PayMethod.CASH,1000000L);
    }
    @DisplayName("Not null 체크")
    @Test()
    public void given_when_thenNotNull(){
        // given

        // when

        // then
        assertThat(reserveConcertUseCase).isNotNull();
    }


    @DisplayName("콘서트 예약 하는 테스트")
    @Test()
    public void givenRequestReservation_whenReserve_thenSuccessfullyReserve(){
        // given
        /**
         * 특정 스케쥴에 해당하는 좌석조회
         * */
        List<Seat> seats = seatReader.readSeatsByScheduleId(phsConcertScheduleId);

        /**
         * 해당 좌석에 예약하기 위한 예약 객체 생성
         * */
        ReservationRequestDto reservationRequestDto = ReservationRequestDto.
                                                                    builder().
                                                                    seats(seats).
                                                                    userId(userId).
                                                                    scheduleId(phsConcertScheduleId).
                                                                    build();
        int beforeCnt = reservationReader.readReservationsByUserId(userId).size();

        /**
         * 예약 UseCase 실행
         * */
        // when
        reserveConcertUseCase.execute(seats, userId);

        int afterCnt = beforeCnt + 1;


        // then
        /**
         * 예약이 잘 되었는지 확인하기 위해 Reservation 정보 조회
         * */
        List<Reservation> reservations = reservationReader.readReservationsByUserId(userId);
        /**
         * 예약 객체가 추가되었기 때문에 count가 +1됨을 확인
         * */
        assertThat(reservations.size()).isEqualTo(afterCnt);
    }

    @DisplayName("[성공] 콘서트 예약 결제 하는 테스트")
    @Test()
    public void givenRequestPayment_whenReserve_thenSuccessfullyReserve(){
        // given
        List<Seat> seats = seatReader.readSeatsByScheduleId(phsConcertScheduleId);

        ReservationRequestDto reservationRequestDto = ReservationRequestDto.
                builder().
                seats(seats).
                userId(userId).
                scheduleId(phsConcertScheduleId).
                build();
        reserveConcertUseCase.execute(seats,userId);
        
        List<Reservation> reservations = reservationReader.readReservationsByUserId(userId);

        Reservation reservation = reservations.get(0);

        PaymentRequestDto paymentRequestDto = PaymentRequestDto.
                builder().
                reservationId(reservation.getId()).
                payMethod(PayMethod.CASH).build();

        // when
        payReservation.execute(paymentRequestDto, userId);
        
        
        // then
        List<Payment> paymentHistoryies = paymentReader.readAllPaymentsOfUserId(userId);


        assertThat(paymentHistoryies.size()).isEqualTo(1L);
        Payment result = paymentHistoryies.get(0);

        assertThat(result.getUserId()).isEqualTo(userId);
    }

}
