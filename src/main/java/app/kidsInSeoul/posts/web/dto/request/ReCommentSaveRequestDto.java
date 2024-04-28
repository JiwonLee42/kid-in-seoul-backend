package app.kidsInSeoul.posts.web.dto.request;


import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.repository.Comment;
import app.kidsInSeoul.posts.repository.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReCommentSaveRequestDto {
    private String content;
    private Member member;
    private Long postId;

    private Long commentId;

    @Builder
    public ReCommentSaveRequestDto(String content, Member member,Long postId, Long commentId){
        this.content = content;
        this.member = member;
        this.postId = postId;
        this.commentId = commentId;
    }

    public Comment toEntity(Member member,Posts post, Comment parentComment) {
        Comment newComment = Comment.builder()
                .content(content)
                .member(member)
                .post(post)
                .parentComment(parentComment)
                .is_recomment(true)
                .build();

        return newComment;
    }
}
