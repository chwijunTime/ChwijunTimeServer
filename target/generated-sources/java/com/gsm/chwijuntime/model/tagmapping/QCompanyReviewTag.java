package com.gsm.chwijuntime.model.tagmapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompanyReviewTag is a Querydsl query type for CompanyReviewTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCompanyReviewTag extends EntityPathBase<CompanyReviewTag> {

    private static final long serialVersionUID = -743727957L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompanyReviewTag companyReviewTag = new QCompanyReviewTag("companyReviewTag");

    public final com.gsm.chwijuntime.model.QCompanyReview companyReview;

    public final NumberPath<Long> CompanyReviewTagIdx = createNumber("CompanyReviewTagIdx", Long.class);

    public final com.gsm.chwijuntime.model.QTag tag;

    public QCompanyReviewTag(String variable) {
        this(CompanyReviewTag.class, forVariable(variable), INITS);
    }

    public QCompanyReviewTag(Path<? extends CompanyReviewTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCompanyReviewTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCompanyReviewTag(PathMetadata metadata, PathInits inits) {
        this(CompanyReviewTag.class, metadata, inits);
    }

    public QCompanyReviewTag(Class<? extends CompanyReviewTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.companyReview = inits.isInitialized("companyReview") ? new com.gsm.chwijuntime.model.QCompanyReview(forProperty("companyReview"), inits.get("companyReview")) : null;
        this.tag = inits.isInitialized("tag") ? new com.gsm.chwijuntime.model.QTag(forProperty("tag")) : null;
    }

}

