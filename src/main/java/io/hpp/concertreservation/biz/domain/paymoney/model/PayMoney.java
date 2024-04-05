package io.hpp.concertreservation.biz.domain.paymoney.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
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

    protected PayMoney(){};

    private PayMoney(Long balance) {
        this.balance = balance;
    }

    public static PayMoney of(Long balance){
        return new PayMoney(balance);
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
