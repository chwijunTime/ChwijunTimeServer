package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApplicationEmployment is a Querydsl query type for ApplicationEmployment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QApplicationEmployment extends EntityPathBase<ApplicationEmployment> {

    private static final long serialVersionUID = 699029224L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplicationEmployment applicationEmployment = new QApplicationEmployment("applicationEmployment");

    public final DatePath<java.time.LocalDate> applicationDate = createDate("applicationDate", java.time.LocalDate.class);

    public final NumberPath<Long> applicationEmploymentIdx = createNumber("applicationEmploymentIdx", Long.class);

    public final StringPath applicationEmploymentPortfolioURL = createString("applicationEmploymentPortfolioURL");

    public final StringPath applicationEmploymentResumeURL = createString("applicationEmploymentResumeURL");

    public final EnumPath<ApplicationEmploymentStatus> applicationEmploymentStatus = createEnum("applicationEmploymentStatus", ApplicationEmploymentStatus.class);

    public final QEmploymentAnnouncement employmentAnnouncement;

    public final StringPath gitHubURL = createString("gitHubURL");

    public final QMember member;

    public QApplicationEmployment(String variable) {
        this(ApplicationEmployment.class, forVariable(variable), INITS);
    }

    public QApplicationEmployment(Path<? extends ApplicationEmployment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApplicationEmployment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApplicationEmployment(PathMetadata metadata, PathInits inits) {
        this(ApplicationEmployment.class, metadata, inits);
    }

    public QApplicationEmployment(Class<? extends ApplicationEmployment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employmentAnnouncement = inits.isInitialized("employmentAnnouncement") ? new QEmploymentAnnouncement(forProperty("employmentAnnouncement"), inits.get("employmentAnnouncement")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

