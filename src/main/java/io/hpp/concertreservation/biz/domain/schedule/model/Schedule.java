package io.hpp.concertreservation.biz.domain.schedule.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Table
@Entity
@ToString
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Column(nullable = false)
    private Long concertId;

    @Setter
    @Column(nullable = false)
    private LocalDateTime performDate;

    protected Schedule(){};
    private Schedule(Long concertId, LocalDateTime performDate) {
        this.concertId = concertId;
        this.performDate = performDate;
    }

    public static Schedule of(Long concertId, LocalDateTime performDate){
        return new Schedule(concertId, performDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
