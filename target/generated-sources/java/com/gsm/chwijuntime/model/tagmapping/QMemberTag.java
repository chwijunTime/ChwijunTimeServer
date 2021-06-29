package com.gsm.chwijuntime.model.tagmapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberTag is a Querydsl query type for MemberTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberTag extends EntityPathBase<MemberTag> {

    private static final long serialVersionUID = -1666073126L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberTag memberTag = new QMemberTag("memberTag");

    public final com.gsm.chwijuntime.model.QMember member;

    public final NumberPath<Long> MemberTagIdx = createNumber("MemberTagIdx", Long.class);

    public final com.gsm.chwijuntime.model.QTag tag;

    public QMemberTag(String variable) {
        this(MemberTag.class, forVariable(variable), INITS);
    }

    public QMemberTag(Path<? extends MemberTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberTag(PathMetadata metadata, PathInits inits) {
        this(MemberTag.class, metadata, inits);
    }

    public QMemberTag(Class<? extends MemberTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.gsm.chwijuntime.model.QMember(forProperty("member")) : null;
        this.tag = inits.isInitialized("tag") ? new com.gsm.chwijuntime.model.QTag(forProperty("tag")) : null;
    }

}

