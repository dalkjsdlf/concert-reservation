package io.hpp.concertreservation.common.config;

import io.hpp.concertreservation.common.aop.TokenValidationInterceptor;
import io.hpp.concertreservation.common.serialize.PayMethodConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final TokenValidationInterceptor tokenValidationInterceptor;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new PayMethodConvertor());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(tokenValidationInterceptor);
    }
}