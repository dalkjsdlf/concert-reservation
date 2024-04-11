package io.hpp.concertreservation.biz.domain.waitqueue.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
public class WaitQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 10)
    @Setter
    private String token;

    @Column(nullable = false)
    @Setter
    @Enumerated(EnumType.STRING)
    private WaitStatus status;

    @Column(nullable = false)
    @Setter
    private LocalDateTime updateTime;

}
