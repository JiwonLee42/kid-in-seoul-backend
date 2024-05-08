package app.kidsInSeoul.posts.web.dto.response;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.repository.Comment;
import app.kidsInSeoul.posts.repository.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long comment_id;
    private String content;

    private Long member_id;
    private Long postId;
    private LocalDateTime createdDate;
    private String author;


    @Builder
    public CommentResponseDto(Comment entity){
        this.comment_id = entity.getId();
        this.content = entity.getContent();
        this.member_id = entity.getMember().getId();
        this.author = entity.getMember().getName();
        this.postId = entity.getId();
        this.createdDate = entity.getCreatedDate();
    }


}
