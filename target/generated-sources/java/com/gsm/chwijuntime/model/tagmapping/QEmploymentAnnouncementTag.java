package com.gsm.chwijuntime.model.tagmapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmploymentAnnouncementTag is a Querydsl query type for EmploymentAnnouncementTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmploymentAnnouncementTag extends EntityPathBase<EmploymentAnnouncementTag> {

    private static final long serialVersionUID = -1207768447L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmploymentAnnouncementTag employmentAnnouncementTag = new QEmploymentAnnouncementTag("employmentAnnouncementTag");

    public final com.gsm.chwijuntime.model.QEmploymentAnnouncement employmentAnnouncement;

    public final NumberPath<Long> EmploymentAnnouncementTagIdx = createNumber("EmploymentAnnouncementTagIdx", Long.class);

    public final com.gsm.chwijuntime.model.QTag tag;

    public QEmploymentAnnouncementTag(String variable) {
        this(EmploymentAnnouncementTag.class, forVariable(variable), INITS);
    }

    public QEmploymentAnnouncementTag(Path<? extends EmploymentAnnouncementTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmploymentAnnouncementTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmploymentAnnouncementTag(PathMetadata metadata, PathInits inits) {
        this(EmploymentAnnouncementTag.class, metadata, inits);
    }

    public QEmploymentAnnouncementTag(Class<? extends EmploymentAnnouncementTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employmentAnnouncement = inits.isInitialized("employmentAnnouncement") ? new com.gsm.chwijuntime.model.QEmploymentAnnouncement(forProperty("employmentAnnouncement"), inits.get("employmentAnnouncement")) : null;
        this.tag = inits.isInitialized("tag") ? new com.gsm.chwijuntime.model.QTag(forProperty("tag")) : null;
    }

}

