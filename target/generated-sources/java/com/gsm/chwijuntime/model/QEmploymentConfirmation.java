package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmploymentConfirmation is a Querydsl query type for EmploymentConfirmation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmploymentConfirmation extends EntityPathBase<EmploymentConfirmation> {

    private static final long serialVersionUID = -1995538891L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmploymentConfirmation employmentConfirmation = new QEmploymentConfirmation("employmentConfirmation");

    public final StringPath employmentConfirmationAddress = createString("employmentConfirmationAddress");

    public final StringPath employmentConfirmationAreas = createString("employmentConfirmationAreas");

    public final StringPath employmentConfirmationEtc = createString("employmentConfirmationEtc");

    public final StringPath employmentConfirmationGeneration = createString("employmentConfirmationGeneration");

    public final NumberPath<Long> employmentConfirmationIdx = createNumber("employmentConfirmationIdx", Long.class);

    public final StringPath employmentConfirmationName = createString("employmentConfirmationName");

    public final StringPath employmentConfirmationSite = createString("employmentConfirmationSite");

    public final QMember member;

    public final StringPath studentName = createString("studentName");

    public QEmploymentConfirmation(String variable) {
        this(EmploymentConfirmation.class, forVariable(variable), INITS);
    }

    public QEmploymentConfirmation(Path<? extends EmploymentConfirmation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmploymentConfirmation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmploymentConfirmation(PathMetadata metadata, PathInits inits) {
        this(EmploymentConfirmation.class, metadata, inits);
    }

    public QEmploymentConfirmation(Class<? extends EmploymentConfirmation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

