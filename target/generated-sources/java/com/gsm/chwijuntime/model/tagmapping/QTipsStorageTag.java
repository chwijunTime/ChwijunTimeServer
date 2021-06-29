package com.gsm.chwijuntime.model.tagmapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTipsStorageTag is a Querydsl query type for TipsStorageTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTipsStorageTag extends EntityPathBase<TipsStorageTag> {

    private static final long serialVersionUID = -230396547L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTipsStorageTag tipsStorageTag = new QTipsStorageTag("tipsStorageTag");

    public final com.gsm.chwijuntime.model.QTag tag;

    public final com.gsm.chwijuntime.model.QTipsStorage tipsStorage;

    public final NumberPath<Long> TipsStorageTagIdx = createNumber("TipsStorageTagIdx", Long.class);

    public QTipsStorageTag(String variable) {
        this(TipsStorageTag.class, forVariable(variable), INITS);
    }

    public QTipsStorageTag(Path<? extends TipsStorageTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTipsStorageTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTipsStorageTag(PathMetadata metadata, PathInits inits) {
        this(TipsStorageTag.class, metadata, inits);
    }

    public QTipsStorageTag(Class<? extends TipsStorageTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tag = inits.isInitialized("tag") ? new com.gsm.chwijuntime.model.QTag(forProperty("tag")) : null;
        this.tipsStorage = inits.isInitialized("tipsStorage") ? new com.gsm.chwijuntime.model.QTipsStorage(forProperty("tipsStorage"), inits.get("tipsStorage")) : null;
    }

}

