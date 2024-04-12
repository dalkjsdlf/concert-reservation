package io.hpp.concertreservation.biz.domain.paymoney.model;

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
    private Long userId;

    @Column
    @Setter
    private Long balance;

    @Column
    @Setter
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    protected PayMoney(){};

    private PayMoney(Long userId, Long balance, PayMethod payMethod) {
        this.userId = userId;
        this.balance = balance;
        this.payMethod = payMethod;
    }

    public static PayMoney of(Long userId, Long balance, PayMethod payMethod){
        return new PayMoney(userId, balance, payMethod);
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
