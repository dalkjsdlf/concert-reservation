package io.hpp.concertreservation.biz.domain.payment.model;

import io.hpp.concertreservation.biz.domain.payment.enumclass.PayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.relational.core.mapping.Table;

@ToString
@Getter
@Table
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long userId;

    private Long balance;
    private PayType type;

}
