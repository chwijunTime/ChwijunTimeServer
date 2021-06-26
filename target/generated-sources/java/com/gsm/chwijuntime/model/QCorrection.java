package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCorrection is a Querydsl query type for Correction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCorrection extends EntityPathBase<Correction> {

    private static final long serialVersionUID = 127199570L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCorrection correction = new QCorrection("correction");

    public final StringPath classNumber = createString("classNumber");

    public final QCorrectionApply correctionApply;

    public final StringPath correctionContent = createString("correctionContent");

    public final NumberPath<Long> correctionIdx = createNumber("correctionIdx", Long.class);

    public final StringPath reasonForRejection = createString("reasonForRejection");

    public QCorrection(String variable) {
        this(Correction.class, forVariable(variable), INITS);
    }

    public QCorrection(Path<? extends Correction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCorrection(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCorrection(PathMetadata metadata, PathInits inits) {
        this(Correction.class, metadata, inits);
    }

    public QCorrection(Class<? extends Correction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.correctionApply = inits.isInitialized("correctionApply") ? new QCorrectionApply(forProperty("correctionApply"), inits.get("correctionApply")) : null;
    }

}

