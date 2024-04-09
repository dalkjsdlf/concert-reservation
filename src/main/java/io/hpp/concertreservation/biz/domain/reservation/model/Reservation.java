package io.hpp.concertreservation.biz.domain.reservation.model;

import io.hpp.concertreservation.biz.domain.reservation.enumclass.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@Table
@Entity
public class Reservation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Setter
    @Column(nullable = false)
    private Long userId;

    @Setter
    @Column(nullable = false)
    private Long scheduleId;

    @Setter
    @Column(nullable = false)
    private LocalDateTime reserveDate;

    @Setter
    @Column(nullable = false)
    private Integer numOfSeats;

    @Setter
    @Column(nullable = false)
    private Long totalPrice;

    @Setter
    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    protected Reservation() {};

    private Reservation(Long userId, Long scheduleId, LocalDateTime reserveDate, Integer numOfSeats, Long totalPrice, PaymentStatus status) {
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.reserveDate = reserveDate;
        this.numOfSeats = numOfSeats;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public static Reservation of(Long userId, Long scheduleId, LocalDateTime reserveDate, Integer numOfSeats, Long totalPrice, PaymentStatus status){
        return new Reservation(userId, scheduleId, reserveDate, numOfSeats, totalPrice, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
