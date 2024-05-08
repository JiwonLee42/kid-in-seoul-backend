package app.kidsInSeoul.posts.repository;

import app.kidsInSeoul.BaseTimeEntity;
import app.kidsInSeoul.member.repository.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts post;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_comment_id", nullable = true) // 부모 댓글
    private Comment parentComment; // 부모 댓글
    @OneToMany(mappedBy = "parentComment",cascade = CascadeType.REMOVE)
    private List<Comment> childComments = new ArrayList<>(); // 자식 댓글
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(name = "is_recomment")
    @ColumnDefault("false")
    private Boolean is_recomment;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

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
