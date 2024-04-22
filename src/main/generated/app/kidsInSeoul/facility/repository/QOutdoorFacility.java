package app.kidsInSeoul.facility.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOutdoorFacility is a Querydsl query type for OutdoorFacility
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOutdoorFacility extends EntityPathBase<OutdoorFacility> {

    private static final long serialVersionUID = -1852044595L;

    public static final QOutdoorFacility outdoorFacility = new QOutdoorFacility("outdoorFacility");

    public final StringPath address = createString("address");

    public final StringPath ageClassification = createString("ageClassification");

    public final StringPath detailAddress = createString("detailAddress");

    public final NumberPath<Integer> fee = createNumber("fee", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isFree = createBoolean("isFree");

    public final NumberPath<java.math.BigDecimal> latitude = createNumber("latitude", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> longitude = createNumber("longitude", java.math.BigDecimal.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> postNum = createNumber("postNum", Integer.class);

    public final StringPath regionGu = createString("regionGu");

    public final StringPath urlLink = createString("urlLink");

    public QOutdoorFacility(String variable) {
        super(OutdoorFacility.class, forVariable(variable));
    }

    public QOutdoorFacility(Path<? extends OutdoorFacility> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOutdoorFacility(PathMetadata metadata) {
        super(OutdoorFacility.class, metadata);
    }

}

