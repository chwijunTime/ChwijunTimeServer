package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTipsStorage is a Querydsl query type for TipsStorage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTipsStorage extends EntityPathBase<TipsStorage> {

    private static final long serialVersionUID = -1201181521L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTipsStorage tipsStorage = new QTipsStorage("tipsStorage");

    public final QMember member;

    public final StringPath tipsInfo = createString("tipsInfo");

    public final NumberPath<Long> tipsStorageIdx = createNumber("tipsStorageIdx", Long.class);

    public final StringPath workCompanyAddress = createString("workCompanyAddress");

    public final StringPath workCompanyName = createString("workCompanyName");

    public QTipsStorage(String variable) {
        this(TipsStorage.class, forVariable(variable), INITS);
    }

    public QTipsStorage(Path<? extends TipsStorage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTipsStorage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTipsStorage(PathMetadata metadata, PathInits inits) {
        this(TipsStorage.class, metadata, inits);
    }

    public QTipsStorage(Class<? extends TipsStorage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

