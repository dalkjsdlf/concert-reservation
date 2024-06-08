package io.hpp.concertreservation.biz.domain.waitqueue.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
@Getter
public class WaitMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String token;

    @Column(nullable = false)
    @Setter
    private Long userId;

    @Column(nullable = false)
    @Setter
    @Enumerated(EnumType.STRING)
    private WaitStatus status;

    @Column(nullable = false)
    @Setter
    private LocalDateTime updateTime;

    protected WaitMember(){};

    private WaitMember(String token, Long userId, WaitStatus status, LocalDateTime updateTime) {
        this.token = token;
        this.userId = userId;
        this.status = status;
        this.updateTime = updateTime;
    }

    public static WaitMember of(String token, Long userId, WaitStatus status, LocalDateTime updateTime){
        return new WaitMember(token, userId, status, updateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaitMember waitMember = (WaitMember) o;
        return Objects.equals(id, waitMember.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
