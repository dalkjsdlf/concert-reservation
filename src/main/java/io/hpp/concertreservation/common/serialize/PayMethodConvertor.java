package io.hpp.concertreservation.common.serialize;


import io.hpp.concertreservation.biz.domain.paymoney.model.PayMethod;
import org.springframework.core.convert.converter.Converter;

public class PayMethodConvertor implements Converter<String, PayMethod> {

    @Override
    public PayMethod convert(String source) {
        return PayMethod.from(source);
    }
}
