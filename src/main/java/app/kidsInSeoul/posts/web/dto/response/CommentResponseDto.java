package app.kidsInSeoul.posts.web.dto.response;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.repository.Comment;
import app.kidsInSeoul.posts.repository.Posts;
import lombok.Builder;

import java.time.LocalDateTime;

public class CommentResponseDto {

    private Long comment_id;
    private String content;
    private Member member;
    private Long postId;
    private LocalDateTime created_at;

    @Builder
    public CommentResponseDto(Comment entity, Posts post){
        this.comment_id = entity.getId();
        this.content = entity.getContent();
        this.member = entity.getMember();
        this.postId = post.getId();
        this.created_at = entity.getCreatedDate();
    }


}
