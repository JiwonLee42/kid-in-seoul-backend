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


    @Builder
    public PostResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.memberId = entity.getMember().getId();
        this.created_at = entity.getCreatedDate();
        this.regionId = entity.getRegion().getId();
    }

}
