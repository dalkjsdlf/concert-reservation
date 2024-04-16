package io.hpp.concertreservation.common.aop;

import io.hpp.concertreservation.biz.domain.waitqueue.component.WaitQueueTokenValidator;
import io.hpp.concertreservation.biz.domain.waitqueue.model.WaitQueue;
import io.hpp.concertreservation.common.annotation.ValidationToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import static io.hpp.concertreservation.common.constants.WebApiConstants.TOKEN_HEADER;
import static io.hpp.concertreservation.common.constants.WebApiConstants.USER_ID_HEADER;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenValidationInterceptor implements HandlerInterceptor {

    private final WaitQueueTokenValidator waitQueueTokenValidator;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("================================  Begin Interceptor ================================");

        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<ValidationToken> valiClass = ValidationToken.class;

        //ValidationToken anntotation이 있는 경우
        if (null != handlerMethod.getMethodAnnotation(valiClass) || null != handlerMethod.getBeanType().getAnnotation(valiClass)) {

            String token  = request.getHeader(TOKEN_HEADER);
            String userId = request.getHeader(USER_ID_HEADER);
            log.info("===================================================="        );
            log.info("            TokenValidationInterceptor              "        );
            log.info("token  : [{}]                                       ", token );
            log.info("userId : [{}]                                       ", userId);
            log.info("===================================================="        );

            return waitQueueTokenValidator.valiation(token);
        }

        log.info("================================  No Annotaion ================================");
        return true;
    }

}
