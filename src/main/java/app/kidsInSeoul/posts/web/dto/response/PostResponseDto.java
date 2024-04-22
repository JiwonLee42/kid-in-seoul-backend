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
    private Member member;
    private String content;

    private LocalDateTime created_at;

    @Builder
    public PostResponseDto(Posts entity){
        this.id = entity.getPost_id();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.member = entity.getMember();
        this.created_at = entity.getCreatedDate();
    }

}
