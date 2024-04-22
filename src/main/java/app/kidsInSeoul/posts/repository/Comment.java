package app.kidsInSeoul.posts.repository;

import app.kidsInSeoul.member.repository.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id") // 부모 댓글
    private Comment parentComment; // 부모 댓글
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childComments = new ArrayList<>(); // 자식 댓글
    @OneToMany
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(name = "is_recomment", columnDefinition = "false")
    private Boolean is_recomment;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @Builder
    public Comment(Posts post, Comment parentComment, List<Comment> childComments, Member member, Boolean is_recomment, String content) {
        this.post = post;
        this.parentComment = parentComment;
        this.childComments = childComments;
        this.member = member;
        this.is_recomment = is_recomment;
        this.content = content;
    }
}
