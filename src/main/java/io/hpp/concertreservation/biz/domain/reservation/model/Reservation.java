package io.hpp.concertreservation.biz.domain.reservation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

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
    private Long numOfSeats;

    @Setter
    @Column(nullable = false)
    private Long totalPrice;

    @Setter
    @Column(nullable = false, length = 1)
    private String paymentYn;

    protected Reservation() {};

    private Reservation(Long userId, Long scheduleId, LocalDateTime reserveDate, Long numOfSeats, Long totalPrice, String paymentYn) {
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.reserveDate = reserveDate;
        this.numOfSeats = numOfSeats;
        this.totalPrice = totalPrice;
        this.paymentYn = paymentYn;
    }

    public static Reservation of(Long userId, Long scheduleId, LocalDateTime reserveDate, Long numOfSeats, Long totalPrice, String paymentYn){
        return new Reservation(userId, scheduleId, reserveDate, numOfSeats, totalPrice, paymentYn);
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
