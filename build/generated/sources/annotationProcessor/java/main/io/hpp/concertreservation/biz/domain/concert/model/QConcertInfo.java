package io.hpp.concertreservation.biz.domain.concert.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConcertInfo is a Querydsl query type for ConcertInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConcertInfo extends EntityPathBase<Concert> {

    private static final long serialVersionUID = 1383093202L;

    public static final QConcertInfo concertInfo = new QConcertInfo("concertInfo");

    public final StringPath artist = createString("artist");

    public final StringPath concertName = createString("concertName");

    public final StringPath conertDesc = createString("conertDesc");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public QConcertInfo(String variable) {
        super(Concert.class, forVariable(variable));
    }

    public QConcertInfo(Path<? extends Concert> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConcertInfo(PathMetadata metadata) {
        super(Concert.class, metadata);
    }

}

