package io.hpp.concertreservation.biz.domain.paymoney.model;

import io.hpp.concertreservation.biz.domain.paymoney.enumclass.PayMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Table
@Entity
public class PayMoney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @Setter
    private Long balance;

    @Column
    @Setter
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    protected PayMoney(){};

    private PayMoney(Long balance,PayMethod payMethod) {
        this.balance = balance;
        this.payMethod = payMethod;
    }

    public static PayMoney of(Long balance,PayMethod payMethod){
        return new PayMoney(balance, payMethod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PayMoney payMoney = (PayMoney) o;
        return Objects.equals(id, payMoney.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
