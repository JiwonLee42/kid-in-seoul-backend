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

    private Long postId;

    @Builder
    public CommentSaveRequestDto(String content,Long postId){
        this.content = content;
        this.postId = postId;
    }

    public Comment toEntity(Member member, Posts post) {
        return Comment.builder()
                .content(content)
                .member(member)
                .post(post)
                .build();
    }

}
