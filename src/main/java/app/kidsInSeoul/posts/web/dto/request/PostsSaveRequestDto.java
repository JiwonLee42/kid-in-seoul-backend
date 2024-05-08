package app.kidsInSeoul.posts.web.dto.request;


import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.repository.Posts;
import app.kidsInSeoul.region.repository.Region;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;


    @Builder
    public PostsSaveRequestDto(String title, String content,Member member){
        this.title = title;
        this.content = content;
    }

    public Posts toEntity(Member member, Region region) {
        return Posts.builder()
                .title(title)
                .content(content)
                .member(member)
                .region(region)
                .build();
    }

}
