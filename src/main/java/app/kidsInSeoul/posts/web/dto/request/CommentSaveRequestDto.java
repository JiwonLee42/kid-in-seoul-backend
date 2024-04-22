package app.kidsInSeoul.posts.web.dto.request;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.repository.Comment;
import app.kidsInSeoul.posts.repository.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {

    private String content;
    private Member member;
    private Posts post;

    @Builder
    public CommentSaveRequestDto(String content,Member member,Posts post){
        this.content = content;
        this.member = member;
        this.post = post;
    }

    public Comment toEntity(Member member) {
        return Comment.builder()
                .content(content)
                .member(member)
                .post(post)
                .build();
    }

}
