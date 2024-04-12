package io.hpp.concertreservation.biz.api.paymoney.controller;

import io.hpp.concertreservation.biz.api.paymoney.dto.PayMoneyResponseDto;

import io.hpp.concertreservation.biz.api.paymoney.usecase.ChargePayMoneyUseCase;
import io.hpp.concertreservation.biz.api.paymoney.usecase.GetPayMoneyUseCase;
import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RequestMapping("/api/paymoney")
@RestController

public class PayMoneyController {

    private final ChargePayMoneyUseCase chargePayMoneyUseCase;
    private final GetPayMoneyUseCase getPayMoneyUseCase;

    public PayMoneyController(@Autowired ChargePayMoneyUseCase chargePayMoneyUseCase,
                              @Autowired GetPayMoneyUseCase getPayMoneyUseCase) {
        this.chargePayMoneyUseCase = chargePayMoneyUseCase;
        this.getPayMoneyUseCase = getPayMoneyUseCase;
    }

    /*
     * /api/paymoney/
     * */
    @GetMapping("")
    public ResponseEntity<PayMoneyResponseDto> getPayMoneyBalance(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId
            @RequestParam
    ){

        return ResponseEntity.ok(
                getPayMoneyUseCase.execute(userId,)
        );
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
        return ResponseEntity.status(HttpStatus.CREATED).body(
                PayMoneyResponseDto.builder()
                .userId(userId)
                .payMethod(PayMethod.CASH)
                .price(10000L)
                .reserveId(null)
                .build());
    }

}
