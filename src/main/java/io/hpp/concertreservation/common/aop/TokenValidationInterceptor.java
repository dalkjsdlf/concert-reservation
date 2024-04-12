package io.hpp.concertreservation.common.aop;

import io.hpp.concertreservation.biz.domain.waitqueue.component.WaitQueueTokenValidator;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;

@Slf4j
public class TokenValidationInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("===============================================");
        log.debug("==================== BEGIN ====================");
        log.debug("Request URI ===> " + request.getRequestURI());

        String token = request.getHeader(TOKEN_HEADER);

        if(StringUtils.isEmpty(token)){

        }else{

        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
