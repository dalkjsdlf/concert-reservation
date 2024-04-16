package io.hpp.concertreservation.biz.api.payment.controller;

import io.hpp.concertreservation.biz.api.payment.dto.PaymentRequestDto;
import io.hpp.concertreservation.biz.api.payment.dto.PaymentResponseDto;
import io.hpp.concertreservation.biz.api.payment.usecase.PayReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RequestMapping("/api/payment")
@RequiredArgsConstructor
@RestController
public class PaymentController {
    private final PayReservation payReservation;

    /*
     * /api/payment/
     * */
    @PostMapping("")
    public ResponseEntity<PaymentResponseDto> payReservation(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @RequestBody PaymentRequestDto paymentRequestDto
            ){
        payReservation.execute(paymentRequestDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
     * /api/payment/refund
     * */
    @PostMapping("refund")
    public ResponseEntity<PaymentResponseDto> refundReservation(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId,
            @RequestBody PaymentRequestDto paymentRequestDto
    ){
        return ResponseEntity.ok(PaymentResponseDto.
                builder().
                reservationid(paymentRequestDto.getReservationId()).
                build());
    }
}
