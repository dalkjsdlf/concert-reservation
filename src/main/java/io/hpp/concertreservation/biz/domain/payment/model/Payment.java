package io.hpp.concertreservation.biz.domain.payment.model;

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
    @Enumerated(EnumType.STRING)
    private TransactionType tranType;

    @Setter
    @Column()
    private Long amount;

    @Setter
    @Column()
    private LocalDateTime tranDate;

    @Setter
    @Column()
    private Long reservationId;

    protected Payment(){};

    private Payment(Long userId, TransactionType tranType, Long amount, LocalDateTime tranDate,Long reservationId) {
        this.userId        = userId;
        this.tranType      = tranType;
        this.amount        = amount;
        this.tranDate     = tranDate;
        this.reservationId = reservationId;
    }

    public static Payment of(Long userId, TransactionType tranType, Long amount, LocalDateTime tranDate, Long reservationId){
        return new Payment(userId, tranType, amount, tranDate, reservationId);
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
