package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConsultingUser is a Querydsl query type for ConsultingUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConsultingUser extends EntityPathBase<ConsultingUser> {

    private static final long serialVersionUID = -458626731L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsultingUser consultingUser = new QConsultingUser("consultingUser");

    public final QConsultingAdmin consultingAdmin;

    public final StringPath consultingUserClassNumber = createString("consultingUserClassNumber");

    public final NumberPath<Long> consultingUserIdx = createNumber("consultingUserIdx", Long.class);

    public final StringPath consultingUserName = createString("consultingUserName");

    public final QMember member;

    public QConsultingUser(String variable) {
        this(ConsultingUser.class, forVariable(variable), INITS);
    }

    public QConsultingUser(Path<? extends ConsultingUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConsultingUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConsultingUser(PathMetadata metadata, PathInits inits) {
        this(ConsultingUser.class, metadata, inits);
    }

    public QConsultingUser(Class<? extends ConsultingUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.consultingAdmin = inits.isInitialized("consultingAdmin") ? new QConsultingAdmin(forProperty("consultingAdmin"), inits.get("consultingAdmin")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

