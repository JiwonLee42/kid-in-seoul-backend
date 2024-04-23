package app.kidsInSeoul.posts.web.response;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.repository.Comment;
import app.kidsInSeoul.posts.repository.Posts;
import lombok.Builder;

import java.time.LocalDateTime;

public class CommentResponseDto {

    private Long comment_id;
    private String content;
    private Member member;
    private Posts post;
    private LocalDateTime created_at;

    @Builder
    public CommentResponseDto(Comment entity){
        this.comment_id = entity.getComment_id();
        this.content = entity.getContent();
        this.member = entity.getMember();
        this.post = entity.getPost();
        this.created_at = entity.getCreatedDate();
    }


}
