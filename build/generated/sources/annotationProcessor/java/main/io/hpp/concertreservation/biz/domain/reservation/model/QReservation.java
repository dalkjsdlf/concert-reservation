package io.hpp.concertreservation.biz.domain.reservation.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = 205002064L;

    public static final QReservation reservation = new QReservation("reservation");

    public final io.hpp.concertreservation.common.entity.QAuditableFields _super = new io.hpp.concertreservation.common.entity.QAuditableFields(this);

    public final NumberPath<Long> concertId = createNumber("concertId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final NumberPath<Long> numOfSeats = createNumber("numOfSeats", Long.class);

    public final StringPath paymentYn = createString("paymentYn");

    public final DateTimePath<java.time.LocalDateTime> reserveDate = createDateTime("reserveDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> totalPrice = createNumber("totalPrice", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QReservation(String variable) {
        super(Reservation.class, forVariable(variable));
    }

    public QReservation(Path<? extends Reservation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Reservation.class, metadata);
    }

}

