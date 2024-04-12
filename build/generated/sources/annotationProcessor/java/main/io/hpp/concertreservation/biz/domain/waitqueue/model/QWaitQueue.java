package io.hpp.concertreservation.biz.domain.waitqueue.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWaitQueue is a Querydsl query type for WaitQueue
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWaitQueue extends EntityPathBase<WaitQueue> {

    private static final long serialVersionUID = 132871888L;

    public static final QWaitQueue waitQueue = new QWaitQueue("waitQueue");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<WaitStatus> status = createEnum("status", WaitStatus.class);

    public final StringPath token = createString("token");

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public QWaitQueue(String variable) {
        super(WaitQueue.class, forVariable(variable));
    }

    public QWaitQueue(Path<? extends WaitQueue> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWaitQueue(PathMetadata metadata) {
        super(WaitQueue.class, metadata);
    }

}

