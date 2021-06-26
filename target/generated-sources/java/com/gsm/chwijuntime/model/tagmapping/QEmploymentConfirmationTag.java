package com.gsm.chwijuntime.model.tagmapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmploymentConfirmationTag is a Querydsl query type for EmploymentConfirmationTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmploymentConfirmationTag extends EntityPathBase<EmploymentConfirmationTag> {

    private static final long serialVersionUID = -2095571757L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmploymentConfirmationTag employmentConfirmationTag = new QEmploymentConfirmationTag("employmentConfirmationTag");

    public final com.gsm.chwijuntime.model.QEmploymentConfirmation employmentConfirmation;

    public final NumberPath<Long> EmploymentConfirmationTagIdx = createNumber("EmploymentConfirmationTagIdx", Long.class);

    public final com.gsm.chwijuntime.model.QTag tag;

    public QEmploymentConfirmationTag(String variable) {
        this(EmploymentConfirmationTag.class, forVariable(variable), INITS);
    }

    public QEmploymentConfirmationTag(Path<? extends EmploymentConfirmationTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmploymentConfirmationTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmploymentConfirmationTag(PathMetadata metadata, PathInits inits) {
        this(EmploymentConfirmationTag.class, metadata, inits);
    }

    public QEmploymentConfirmationTag(Class<? extends EmploymentConfirmationTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employmentConfirmation = inits.isInitialized("employmentConfirmation") ? new com.gsm.chwijuntime.model.QEmploymentConfirmation(forProperty("employmentConfirmation"), inits.get("employmentConfirmation")) : null;
        this.tag = inits.isInitialized("tag") ? new com.gsm.chwijuntime.model.QTag(forProperty("tag")) : null;
    }

}

