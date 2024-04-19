package app.kidsInSeoul.posts.repository;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPosts is a Querydsl query type for Posts
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPosts extends EntityPathBase<Posts> {

    private static final long serialVersionUID = -1499626301L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPosts posts = new QPosts("posts");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final app.kidsInSeoul.member.repository.QMember member;

    public final NumberPath<Long> post_id = createNumber("post_id", Long.class);

    public final app.kidsInSeoul.region.repository.QRegion region;

    public final StringPath title = createString("title");

    public QPosts(String variable) {
        this(Posts.class, forVariable(variable), INITS);
    }

    public QPosts(Path<? extends Posts> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPosts(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPosts(PathMetadata metadata, PathInits inits) {
        this(Posts.class, metadata, inits);
    }

    public QPosts(Class<? extends Posts> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new app.kidsInSeoul.member.repository.QMember(forProperty("member"), inits.get("member")) : null;
        this.region = inits.isInitialized("region") ? new app.kidsInSeoul.region.repository.QRegion(forProperty("region")) : null;
    }

}

