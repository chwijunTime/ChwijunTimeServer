package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberResume is a Querydsl query type for MemberResume
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberResume extends EntityPathBase<MemberResume> {

    private static final long serialVersionUID = -947027877L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberResume memberResume = new QMemberResume("memberResume");

    public final QMember member;

    public final NumberPath<Long> memberResumeIdx = createNumber("memberResumeIdx", Long.class);

    public final StringPath resumeFileURL = createString("resumeFileURL");

    public QMemberResume(String variable) {
        this(MemberResume.class, forVariable(variable), INITS);
    }

    public QMemberResume(Path<? extends MemberResume> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberResume(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberResume(PathMetadata metadata, PathInits inits) {
        this(MemberResume.class, metadata, inits);
    }

    public QMemberResume(Class<? extends MemberResume> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

