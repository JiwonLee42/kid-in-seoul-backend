package app.kidsInSeoul.schedule.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSchedule is a Querydsl query type for Schedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSchedule extends EntityPathBase<Schedule> {

    private static final long serialVersionUID = 1453953085L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchedule schedule = new QSchedule("schedule");

    public final SimplePath<app.kidsInSeoul.facility.repository.ArtEducation> artEducation = createSimple("artEducation", app.kidsInSeoul.facility.repository.ArtEducation.class);

    public final StringPath content = createString("content");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> endTime = createTime("endTime", java.time.LocalTime.class);

    public final app.kidsInSeoul.facility.repository.QKidsCafe kidsCafe;

    public final app.kidsInSeoul.facility.repository.QLibrary library;

    public final app.kidsInSeoul.member.repository.QMember member;

    public final app.kidsInSeoul.facility.repository.QOutdoorFacility outdoorFacility;

    public final app.kidsInSeoul.facility.repository.QPark park;

    public final NumberPath<Long> schedule_id = createNumber("schedule_id", Long.class);

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    public final StringPath title = createString("title");

    public QSchedule(String variable) {
        this(Schedule.class, forVariable(variable), INITS);
    }

    public QSchedule(Path<? extends Schedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSchedule(PathMetadata metadata, PathInits inits) {
        this(Schedule.class, metadata, inits);
    }

    public QSchedule(Class<? extends Schedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.kidsCafe = inits.isInitialized("kidsCafe") ? new app.kidsInSeoul.facility.repository.QKidsCafe(forProperty("kidsCafe")) : null;
        this.library = inits.isInitialized("library") ? new app.kidsInSeoul.facility.repository.QLibrary(forProperty("library")) : null;
        this.member = inits.isInitialized("member") ? new app.kidsInSeoul.member.repository.QMember(forProperty("member"), inits.get("member")) : null;
        this.outdoorFacility = inits.isInitialized("outdoorFacility") ? new app.kidsInSeoul.facility.repository.QOutdoorFacility(forProperty("outdoorFacility")) : null;
        this.park = inits.isInitialized("park") ? new app.kidsInSeoul.facility.repository.QPark(forProperty("park")) : null;
    }

}

