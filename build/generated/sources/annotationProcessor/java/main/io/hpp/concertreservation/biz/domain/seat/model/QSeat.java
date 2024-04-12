package io.hpp.concertreservation.biz.domain.seat.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSeat is a Querydsl query type for Seat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeat extends EntityPathBase<Seat> {

    private static final long serialVersionUID = -723763426L;

    public static final QSeat seat = new QSeat("seat");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> reserveId = createNumber("reserveId", Long.class);

    public final NumberPath<Long> scheduleId = createNumber("scheduleId", Long.class);

    public final EnumPath<SeatGrade> seatGrade = createEnum("seatGrade", SeatGrade.class);

    public final NumberPath<Long> seatNo = createNumber("seatNo", Long.class);

    public QSeat(String variable) {
        super(Seat.class, forVariable(variable));
    }

    public QSeat(Path<? extends Seat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeat(PathMetadata metadata) {
        super(Seat.class, metadata);
    }

}

