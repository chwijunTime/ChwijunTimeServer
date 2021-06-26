package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCorrectionApply is a Querydsl query type for CorrectionApply
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCorrectionApply extends EntityPathBase<CorrectionApply> {

    private static final long serialVersionUID = -1110790852L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCorrectionApply correctionApply = new QCorrectionApply("correctionApply");

    public final NumberPath<Long> correctionApplyIdx = createNumber("correctionApplyIdx", Long.class);

    public final EnumPath<CorrectionStatus> correctionStatus = createEnum("correctionStatus", CorrectionStatus.class);

    public final EnumPath<CorrectionType> correctionType = createEnum("correctionType", CorrectionType.class);

    public final QMember member;

    public final QMemberPortfolio memberPortfolio;

    public final QMemberResume memberResume;

    public QCorrectionApply(String variable) {
        this(CorrectionApply.class, forVariable(variable), INITS);
    }

    public QCorrectionApply(Path<? extends CorrectionApply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCorrectionApply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCorrectionApply(PathMetadata metadata, PathInits inits) {
        this(CorrectionApply.class, metadata, inits);
    }

    public QCorrectionApply(Class<? extends CorrectionApply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.memberPortfolio = inits.isInitialized("memberPortfolio") ? new QMemberPortfolio(forProperty("memberPortfolio"), inits.get("memberPortfolio")) : null;
        this.memberResume = inits.isInitialized("memberResume") ? new QMemberResume(forProperty("memberResume"), inits.get("memberResume")) : null;
    }

}

