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

    public final io.hpp.concertreservation.common.entity.QAuditableFields _super = new io.hpp.concertreservation.common.entity.QAuditableFields(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> reserveId = createNumber("reserveId", Long.class);

    public final StringPath seatGrade = createString("seatGrade");

    public final NumberPath<Long> seatNo = createNumber("seatNo", Long.class);

    public final NumberPath<Long> sheduleId = createNumber("sheduleId", Long.class);

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

