package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberPortfolio is a Querydsl query type for MemberPortfolio
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberPortfolio extends EntityPathBase<MemberPortfolio> {

    private static final long serialVersionUID = -621332902L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberPortfolio memberPortfolio = new QMemberPortfolio("memberPortfolio");

    public final QMember member;

    public final NumberPath<Long> memberPortfolioIdx = createNumber("memberPortfolioIdx", Long.class);

    public final StringPath notionPortfolioURL = createString("notionPortfolioURL");

    public QMemberPortfolio(String variable) {
        this(MemberPortfolio.class, forVariable(variable), INITS);
    }

    public QMemberPortfolio(Path<? extends MemberPortfolio> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberPortfolio(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberPortfolio(PathMetadata metadata, PathInits inits) {
        this(MemberPortfolio.class, metadata, inits);
    }

    public QMemberPortfolio(Class<? extends MemberPortfolio> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

