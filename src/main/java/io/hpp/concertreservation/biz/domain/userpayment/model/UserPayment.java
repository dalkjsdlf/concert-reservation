package io.hpp.concertreservation.biz.domain.userpayment.model;


import io.hpp.concertreservation.biz.domain.userpayment.enumclass.PayMethod;
import io.hpp.concertreservation.biz.domain.userpayment.enumclass.TransactionType;
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
    @Column(name = "pay_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    protected UserPayment(){};
    private UserPayment(Long userId, Long balance, PayMethod payMethod) {
        this.userId = userId;
        this.balance = balance;
        this.payMethod = payMethod;
    }

    public static UserPayment of(Long userId, Long balance, PayMethod payMethod){
        return new UserPayment(userId, balance, payMethod);
    }


}
