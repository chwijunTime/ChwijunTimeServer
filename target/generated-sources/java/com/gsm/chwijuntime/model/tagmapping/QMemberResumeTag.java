package com.gsm.chwijuntime.model.tagmapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberResumeTag is a Querydsl query type for MemberResumeTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberResumeTag extends EntityPathBase<MemberResumeTag> {

    private static final long serialVersionUID = 1499153005L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberResumeTag memberResumeTag = new QMemberResumeTag("memberResumeTag");

    public final com.gsm.chwijuntime.model.QMemberResume memberResume;

    public final NumberPath<Long> MemberResumeTagIdx = createNumber("MemberResumeTagIdx", Long.class);

    public final com.gsm.chwijuntime.model.QTag tag;

    public QMemberResumeTag(String variable) {
        this(MemberResumeTag.class, forVariable(variable), INITS);
    }

    public QMemberResumeTag(Path<? extends MemberResumeTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberResumeTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberResumeTag(PathMetadata metadata, PathInits inits) {
        this(MemberResumeTag.class, metadata, inits);
    }

    public QMemberResumeTag(Class<? extends MemberResumeTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberResume = inits.isInitialized("memberResume") ? new com.gsm.chwijuntime.model.QMemberResume(forProperty("memberResume"), inits.get("memberResume")) : null;
        this.tag = inits.isInitialized("tag") ? new com.gsm.chwijuntime.model.QTag(forProperty("tag")) : null;
    }

}

