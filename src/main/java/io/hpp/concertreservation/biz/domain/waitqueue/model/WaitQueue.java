package io.hpp.concertreservation.biz.domain.waitqueue.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

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

    protected WaitQueue(){};

    private WaitQueue(Long id, String token, WaitStatus status, LocalDateTime updateTime) {
        this.id = id;
        this.token = token;
        this.status = status;
        this.updateTime = updateTime;
    }

    public static WaitQueue of(Long id, String token, WaitStatus status, LocalDateTime updateTime){
        return new WaitQueue(id, token, status, updateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaitQueue waitQueue = (WaitQueue) o;
        return Objects.equals(id, waitQueue.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
