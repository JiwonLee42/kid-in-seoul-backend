package app.kidsInSeoul.facility.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLibrary is a Querydsl query type for Library
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLibrary extends EntityPathBase<Library> {

    private static final long serialVersionUID = 1058310857L;

    public static final QLibrary library = new QLibrary("library");

    public final StringPath homepageUrl = createString("homepageUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> latitude = createNumber("latitude", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> longitude = createNumber("longitude", java.math.BigDecimal.class);

    public final StringPath name = createString("name");

    public final StringPath operatingTime = createString("operatingTime");

    public final StringPath phoneNum = createString("phoneNum");

    public final StringPath postNum = createString("postNum");

    public final StringPath regionGu = createString("regionGu");

    public final StringPath street = createString("street");

    public QLibrary(String variable) {
        super(Library.class, forVariable(variable));
    }

    public QLibrary(Path<? extends Library> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLibrary(PathMetadata metadata) {
        super(Library.class, metadata);
    }

}

