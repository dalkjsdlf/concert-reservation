package io.hpp.concertreservation.biz.domain.userpayment.model;

import io.hpp.concertreservation.biz.domain.payment.enumclass.PayMethod;
import io.hpp.concertreservation.biz.domain.payment.enumclass.TransactionType;
import io.hpp.concertreservation.common.entity.AuditableFields;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.relational.core.mapping.Table;

@ToString
@Getter
@Table
@Entity
public class UserPayment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Setter
    @Column(name = "balance", nullable = false)
    @ColumnDefault("0")
    private Long balance;

    @Setter
    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Setter
    @Column(name = "pay_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

}
