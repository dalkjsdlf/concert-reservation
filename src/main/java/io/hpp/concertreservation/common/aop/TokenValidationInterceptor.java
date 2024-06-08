package io.hpp.concertreservation.common.aop;

import io.hpp.concertreservation.biz.domain.waitqueue.component.TokenGenerator;
import io.hpp.concertreservation.biz.domain.waitqueue.component.QueueAppender;
import io.hpp.concertreservation.biz.domain.waitqueue.component.QueueTokenValidator;
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

    private final QueueTokenValidator waitQueueTokenValidator;
    private final QueueAppender waitQueueAppender;

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

            /*
             * TOKEN이 NULL 일 때
             * */
            if(waitQueueTokenValidator.isTokenEmpty(token)){
                /**
                 * 토큰 생성
                 * */
                token = TokenGenerator.generateToken();
                log.debug("Generated Token Value [{}]",token);
            }else{
                /**
                 * TOKEN 검증
                 * */
                if(waitQueueTokenValidator.isValidated(token)) return true;
                if(waitQueueTokenValidator.isAlreadyInWaitQueue(token)) return true;
            }

            /**
             * TOKEN 추가
             * Active Queue에 추가 시도
             * Wait Queue에 추가 시도
             * */
            waitQueueAppender.addQueue(token);

            return true;
        }

        log.info("================================  No Annotaion ================================");
        return true;
    }

}
