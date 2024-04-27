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

    private Long regionId;

    @Builder
    public PostsSaveRequestDto(String title, String content,Member member, Long regionId){
        this.title = title;
        this.content = content;
        this.member = member;
        this.regionId = regionId;
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
