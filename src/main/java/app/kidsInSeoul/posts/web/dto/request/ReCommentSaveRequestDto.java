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
    private Posts post;
    private Comment parentComment;

    @Builder
    public ReCommentSaveRequestDto(String content, Member member, Posts post, Comment comment){
        this.content = content;
        this.member = member;
        this.post = post;
        this.parentComment = comment;
    }

    public Comment toEntity(Member member) {
        Comment newComment = Comment.builder()
                .content(content)
                .member(member)
                .post(post)
                .parentComment(parentComment)
                .build();

        // 만약 부모 댓글이 있으면, 부모 댓글의 childComments에 새로운 댓글을 추가
        if (parentComment != null) {
            parentComment.getChildComments().add(newComment);
        }

        return newComment;
    }
}
