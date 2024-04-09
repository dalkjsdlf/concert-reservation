package io.hpp.concertreservation.biz.domain.concert.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Table(name = "concert_info")
@Entity
public class Concert {
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

    protected Concert(){};
    private Concert(String concertName, String conertDesc, String artist, LocalDateTime startDate, LocalDateTime endDate) {
        this.concertName = concertName;
        this.conertDesc = conertDesc;
        this.artist = artist;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Concert of(String concertName, String conertDesc, String artist, LocalDateTime startDate, LocalDateTime endDate){
        return new Concert(concertName, conertDesc, artist, startDate, endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concert concert = (Concert) o;
        return Objects.equals(id, concert.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
