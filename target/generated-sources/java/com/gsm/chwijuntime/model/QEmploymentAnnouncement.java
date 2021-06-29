package com.gsm.chwijuntime.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmploymentAnnouncement is a Querydsl query type for EmploymentAnnouncement
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmploymentAnnouncement extends EntityPathBase<EmploymentAnnouncement> {

    private static final long serialVersionUID = 357056327L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmploymentAnnouncement employmentAnnouncement = new QEmploymentAnnouncement("employmentAnnouncement");

    public final DatePath<java.time.LocalDate> announcementDate = createDate("announcementDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> deadLine = createDate("deadLine", java.time.LocalDate.class);

    public final StringPath employmentAnnouncementAddress = createString("employmentAnnouncementAddress");

    public final StringPath employmentAnnouncementEtc = createString("employmentAnnouncementEtc");

    public final StringPath employmentAnnouncementExplanation = createString("employmentAnnouncementExplanation");

    public final NumberPath<Long> employmentAnnouncementIdx = createNumber("employmentAnnouncementIdx", Long.class);

    public final StringPath employmentAnnouncementName = createString("employmentAnnouncementName");

    public final QMember member;

    public final StringPath preferentialConditions = createString("preferentialConditions");

    public final StringPath recruitmentField = createString("recruitmentField");

    public QEmploymentAnnouncement(String variable) {
        this(EmploymentAnnouncement.class, forVariable(variable), INITS);
    }

    public QEmploymentAnnouncement(Path<? extends EmploymentAnnouncement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmploymentAnnouncement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmploymentAnnouncement(PathMetadata metadata, PathInits inits) {
        this(EmploymentAnnouncement.class, metadata, inits);
    }

    public QEmploymentAnnouncement(Class<? extends EmploymentAnnouncement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

