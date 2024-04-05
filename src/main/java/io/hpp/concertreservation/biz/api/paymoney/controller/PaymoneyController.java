package io.hpp.concertreservation.biz.api.paymoney.controller;

import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyResponseDto;
import io.hpp.concertreservation.biz.api.reservation.dto.ReservationResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RequestMapping("/api/paymoney")
@RestController

public class PaymoneyController {
    /*
     * /api/paymoney/
     * */
    @GetMapping("")
    public ResponseEntity<PayMoneyResponseDto> getPaymoneyBalance(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId
    ){
        return ResponseEntity.ok(new PayMoneyResponseDto());
    }
    /*
     * /api/paymoney/histories
     * */
    @GetMapping("histories")
    public ResponseEntity<List<PayMoneyResponseDto>> getHistories(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId
    ){
        return ResponseEntity.ok(new ArrayList<PayMoneyResponseDto>());
    }
    /*
     * /api/paymoney/charge
     * */
    @PatchMapping("charge")
    public ResponseEntity<PayMoneyResponseDto> chargeHistory(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(new PayMoneyResponseDto());
    }

}
