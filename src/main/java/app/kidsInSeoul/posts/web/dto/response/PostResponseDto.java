package app.kidsInSeoul.posts.web.dto.response;


import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.repository.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {

    private Long id;
    private String title;
    private Long memberId;
    private String content;

    private LocalDateTime created_at;

    private Long regionId;

    private int likeNum;

    private String authorName;

    private String authorId;

    @Builder
    public PostResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.memberId = entity.getMember().getId();
        this.authorName = entity.getMember().getNickname();
        this.authorId = entity.getMember().getUserId();
        this.created_at = entity.getCreatedDate();
        this.regionId = entity.getRegion().getId();
        this.likeNum = entity.getLikeNum();
    }

}
