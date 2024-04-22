package app.kidsInSeoul.facility.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QKidsCafe is a Querydsl query type for KidsCafe
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QKidsCafe extends EntityPathBase<KidsCafe> {

    private static final long serialVersionUID = 1057326428L;

    public static final QKidsCafe kidsCafe = new QKidsCafe("kidsCafe");

    public final StringPath address = createString("address");

    public final StringPath availableAge = createString("availableAge");

    public final StringPath closedDays = createString("closedDays");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> latitude = createNumber("latitude", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> longitude = createNumber("longitude", java.math.BigDecimal.class);

    public final StringPath name = createString("name");

    public final StringPath operatingDays = createString("operatingDays");

    public final StringPath phoneNum = createString("phoneNum");

    public final StringPath regionDong = createString("regionDong");

    public final StringPath regionGu = createString("regionGu");

    public final StringPath usageCapacity = createString("usageCapacity");

    public QKidsCafe(String variable) {
        super(KidsCafe.class, forVariable(variable));
    }

    public QKidsCafe(Path<? extends KidsCafe> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKidsCafe(PathMetadata metadata) {
        super(KidsCafe.class, metadata);
    }

}

