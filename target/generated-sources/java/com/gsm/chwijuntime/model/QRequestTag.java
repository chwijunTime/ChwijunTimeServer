package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRequestTag is a Querydsl query type for RequestTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRequestTag extends EntityPathBase<RequestTag> {

    private static final long serialVersionUID = -991371137L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRequestTag requestTag = new QRequestTag("requestTag");

    public final QMember member;

    public final NumberPath<Long> tagIdx = createNumber("tagIdx", Long.class);

    public final StringPath tagName = createString("tagName");

    public QRequestTag(String variable) {
        this(RequestTag.class, forVariable(variable), INITS);
    }

    public QRequestTag(Path<? extends RequestTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRequestTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRequestTag(PathMetadata metadata, PathInits inits) {
        this(RequestTag.class, metadata, inits);
    }

    public QRequestTag(Class<? extends RequestTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

