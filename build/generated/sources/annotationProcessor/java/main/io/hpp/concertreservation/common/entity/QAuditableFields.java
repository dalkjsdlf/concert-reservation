package io.hpp.concertreservation.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuditableFields is a Querydsl query type for AuditableFields
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAuditableFields extends EntityPathBase<AuditableFields> {

    private static final long serialVersionUID = 1852838924L;

    public static final QAuditableFields auditableFields = new QAuditableFields("auditableFields");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public QAuditableFields(String variable) {
        super(AuditableFields.class, forVariable(variable));
    }

    public QAuditableFields(Path<? extends AuditableFields> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuditableFields(PathMetadata metadata) {
        super(AuditableFields.class, metadata);
    }

}

