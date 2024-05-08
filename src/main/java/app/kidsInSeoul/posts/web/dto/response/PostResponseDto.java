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

    private LocalDateTime createdDate;

    private Long regionId;

    private int likeNum;

    private String authorNickname;

    private String authorUserId;

    @Builder
    public PostResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.memberId = entity.getMember().getId();
        this.authorNickname = entity.getMember().getNickname();
        this.authorUserId = entity.getMember().getUserId();
        this.createdDate = entity.getCreatedDate();
        this.regionId = entity.getRegion().getId();
        this.likeNum = entity.getLikeNum();
    }

}
