package app.kidsInSeoul.posts.web.response;


import app.kidsInSeoul.posts.repository.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private Long member_id;
    private String content;

    @Builder
    public PostResponseDto(Posts entity){
        this.id = entity.getPost_id();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }

}
