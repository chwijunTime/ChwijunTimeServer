package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompanyReview is a Querydsl query type for CompanyReview
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCompanyReview extends EntityPathBase<CompanyReview> {

    private static final long serialVersionUID = 2135199681L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompanyReview companyReview = new QCompanyReview("companyReview");

    public final StringPath companyAddress = createString("companyAddress");

    public final NumberPath<Integer> companyCost = createNumber("companyCost", Integer.class);

    public final DatePath<java.time.LocalDate> companyDateofApplication = createDate("companyDateofApplication", java.time.LocalDate.class);

    public final StringPath companyFrequentlyAskedQuestions = createString("companyFrequentlyAskedQuestions");

    public final StringPath companyName = createString("companyName");

    public final NumberPath<Long> companyReviewIdx = createNumber("companyReviewIdx", Long.class);

    public final StringPath companyReviews = createString("companyReviews");

    public final QMember member;

    public QCompanyReview(String variable) {
        this(CompanyReview.class, forVariable(variable), INITS);
    }

    public QCompanyReview(Path<? extends CompanyReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCompanyReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCompanyReview(PathMetadata metadata, PathInits inits) {
        this(CompanyReview.class, metadata, inits);
    }

    public QCompanyReview(Class<? extends CompanyReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

