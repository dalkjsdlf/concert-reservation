package io.hpp.concertreservation.common.config;

import io.hpp.concertreservation.common.aop.TokenValidationInterceptor;
import io.hpp.concertreservation.common.serialize.PayMethodConvertor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new PayMethodConvertor());
    }

    public void addInterceptor(InterceptorRegistry registry) {
            registry.addInterceptor(new TokenValidationInterceptor())
                    .excludePathPatterns("/api/paymoney/*");
    }

//    @Bean
//    public TokenValidationInterceptor tokenValidationInterceptor(){
//        return new TokenValidationInterceptor();
//    }
}