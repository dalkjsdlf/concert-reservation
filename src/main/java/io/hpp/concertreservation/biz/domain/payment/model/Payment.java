package io.hpp.concertreservation.biz.domain.payment.model;

import io.hpp.concertreservation.biz.domain.payment.enumclass.TransactionType;
import io.hpp.concertreservation.biz.domain.reservation.enumclass.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Table
@Entity
public class Payment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column()
    private Long userId;

    @Setter
    @Column()
    private TransactionType tranType;

    @Setter
    @Column()
    private Long amount;

    @Setter
    @Column()
    private LocalDateTime tran_date;



    protected Payment(){};

    private Payment(Long userId, TransactionType tranType, Long amount, LocalDateTime tran_date) {
        this.userId = userId;
        this.tranType = tranType;
        this.amount = amount;
        this.tran_date = tran_date;
    }

    public static Payment of(Long userId, TransactionType tranType, Long amount, LocalDateTime tran_date){
        return new Payment(userId, tranType, amount, tran_date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
