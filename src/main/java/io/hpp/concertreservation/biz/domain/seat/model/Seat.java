package io.hpp.concertreservation.biz.domain.seat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Getter
@Table
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Column(nullable = false)
    private Long sheduleId;

    @Setter
    @Column(nullable = false)
    private Long seatNo;

    @Setter
    @Column(nullable = false)
    private String seatGrade;

    @Setter
    @Column(nullable = false)
    private Long price;

    @Setter
    @Column(nullable = false)
    private Long reserveId;

    protected Seat(){}
    private Seat(Long sheduleId, Long seatNo, String seatGrade, Long price, Long reserveId) {
        this.sheduleId = sheduleId;
        this.seatNo = seatNo;
        this.seatGrade = seatGrade;
        this.price = price;
        this.reserveId = reserveId;
    }

    public static Seat of(Long sheduleId, Long seatNo, String seatGrade, Long price, Long reserveId){
        return new Seat(sheduleId, seatNo, seatGrade, price, reserveId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(id, seat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
