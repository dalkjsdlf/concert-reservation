package io.hpp.concertreservation.biz.api.validation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@RequestMapping("/api/validation")
@RestController
public class ValidationController {


    /*
     * /api/concerts
     * */
    @GetMapping("")
    public ResponseEntity<String> getAllConcerts(
            @RequestHeader(TOKEN_HEADER) String token,
            @RequestHeader(USER_ID_HEADER) Long userId
    ){
        return ResponseEntity.ok("ok");
    }

    /*
     * /api/concerts/{concertId}
     * */
    @GetMapping("issue")
    public ResponseEntity<String> getAllConcerts(
            @RequestHeader(USER_ID_HEADER) Long userId,
            @PathVariable("concertId") Long concertId){
        String token = "token";
        return ResponseEntity.ok(token);
    }

}
