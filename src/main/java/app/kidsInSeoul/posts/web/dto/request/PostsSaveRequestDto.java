package app.kidsInSeoul.posts.web.dto.request;


import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.repository.Posts;
import app.kidsInSeoul.region.repository.Region;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;

    private Member member;

    private Region region;

    @Builder
    public PostsSaveRequestDto(String title, String content,Member member, Region region){
        this.title = title;
        this.content = content;
        this.member = member;
        this.region = region;
    }

    public Posts toEntity(Member member) {
        return Posts.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();
    }

}
