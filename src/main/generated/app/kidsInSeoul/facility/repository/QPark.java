package app.kidsInSeoul.facility.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPark is a Querydsl query type for Park
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPark extends EntityPathBase<Park> {

    private static final long serialVersionUID = -1184208740L;

    public static final QPark park = new QPark("park");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath operatingTime = createString("operatingTime");

    public final StringPath regionGu = createString("regionGu");

    public final StringPath regionSi = createString("regionSi");

    public final StringPath street = createString("street");

    public QPark(String variable) {
        super(Park.class, forVariable(variable));
    }

    public QPark(Path<? extends Park> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPark(PathMetadata metadata) {
        super(Park.class, metadata);
    }

}

