package io.hpp.concertreservation.biz.domain.concert.model;

import io.hpp.concertreservation.common.entity.AuditableFields;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Table(name = "concert_info")
@Entity
public class ConcertInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Column(nullable = false, length = 100)
    private String concertName;

    @Setter
    @Column(nullable = true, length = 1000)
    private String conertDesc;

    @Setter
    @Column(nullable = true, length = 50)
    private String artist;

    @Setter
    @Column(nullable = true)
    private LocalDateTime startDate;

    @Setter
    @Column(nullable = true)
    private LocalDateTime endDate;

    protected ConcertInfo(){};
    private ConcertInfo(String concertName, String conertDesc, String artist, LocalDateTime startDate, LocalDateTime endDate) {
        this.concertName = concertName;
        this.conertDesc = conertDesc;
        this.artist = artist;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static ConcertInfo of(String concertName, String conertDesc, String artist, LocalDateTime startDate, LocalDateTime endDate){
        return new ConcertInfo(concertName, conertDesc, artist, startDate, endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcertInfo concert = (ConcertInfo) o;
        return Objects.equals(id, concert.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
