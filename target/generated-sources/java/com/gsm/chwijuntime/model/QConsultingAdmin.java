package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConsultingAdmin is a Querydsl query type for ConsultingAdmin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConsultingAdmin extends EntityPathBase<ConsultingAdmin> {

    private static final long serialVersionUID = -1351436539L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsultingAdmin consultingAdmin = new QConsultingAdmin("consultingAdmin");

    public final DateTimePath<java.time.LocalDateTime> applicationDate = createDateTime("applicationDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> consultingIdx = createNumber("consultingIdx", Long.class);

    public final EnumPath<ConsultingStatus> consultingStatus = createEnum("consultingStatus", ConsultingStatus.class);

    public final QMember member;

    public QConsultingAdmin(String variable) {
        this(ConsultingAdmin.class, forVariable(variable), INITS);
    }

    public QConsultingAdmin(Path<? extends ConsultingAdmin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConsultingAdmin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConsultingAdmin(PathMetadata metadata, PathInits inits) {
        this(ConsultingAdmin.class, metadata, inits);
    }

    public QConsultingAdmin(Class<? extends ConsultingAdmin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

