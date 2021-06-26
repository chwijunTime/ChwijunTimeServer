package com.gsm.chwijuntime.model.tagmapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberPortfolioTag is a Querydsl query type for MemberPortfolioTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberPortfolioTag extends EntityPathBase<MemberPortfolioTag> {

    private static final long serialVersionUID = 523560690L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberPortfolioTag memberPortfolioTag = new QMemberPortfolioTag("memberPortfolioTag");

    public final com.gsm.chwijuntime.model.QMemberPortfolio memberPortfolio;

    public final NumberPath<Long> MemberPortfolioTagIdx = createNumber("MemberPortfolioTagIdx", Long.class);

    public final com.gsm.chwijuntime.model.QTag tag;

    public QMemberPortfolioTag(String variable) {
        this(MemberPortfolioTag.class, forVariable(variable), INITS);
    }

    public QMemberPortfolioTag(Path<? extends MemberPortfolioTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberPortfolioTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberPortfolioTag(PathMetadata metadata, PathInits inits) {
        this(MemberPortfolioTag.class, metadata, inits);
    }

    public QMemberPortfolioTag(Class<? extends MemberPortfolioTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberPortfolio = inits.isInitialized("memberPortfolio") ? new com.gsm.chwijuntime.model.QMemberPortfolio(forProperty("memberPortfolio"), inits.get("memberPortfolio")) : null;
        this.tag = inits.isInitialized("tag") ? new com.gsm.chwijuntime.model.QTag(forProperty("tag")) : null;
    }

}

