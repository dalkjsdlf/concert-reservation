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

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> numOfSeats = createNumber("numOfSeats", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> reserveDate = createDateTime("reserveDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> scheduleId = createNumber("scheduleId", Long.class);

    public final EnumPath<PaymentStatus> status = createEnum("status", PaymentStatus.class);

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

