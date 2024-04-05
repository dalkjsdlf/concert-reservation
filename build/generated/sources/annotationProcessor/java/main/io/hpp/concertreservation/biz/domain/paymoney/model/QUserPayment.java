package io.hpp.concertreservation.biz.domain.paymoney.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserPayment is a Querydsl query type for UserPayment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPayment extends EntityPathBase<UserPayment> {

    private static final long serialVersionUID = 267310990L;

    public static final QUserPayment userPayment = new QUserPayment("userPayment");

    public final io.hpp.concertreservation.common.entity.QAuditableFields _super = new io.hpp.concertreservation.common.entity.QAuditableFields(this);

    public final NumberPath<Long> balance = createNumber("balance", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final EnumPath<io.hpp.concertreservation.biz.domain.payment.enumclass.PayMethod> payMethod = createEnum("payMethod", io.hpp.concertreservation.biz.domain.payment.enumclass.PayMethod.class);

    public final EnumPath<io.hpp.concertreservation.biz.domain.payment.enumclass.TransactionType> type = createEnum("type", io.hpp.concertreservation.biz.domain.payment.enumclass.TransactionType.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserPayment(String variable) {
        super(UserPayment.class, forVariable(variable));
    }

    public QUserPayment(Path<? extends UserPayment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPayment(PathMetadata metadata) {
        super(UserPayment.class, metadata);
    }

}

