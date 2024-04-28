package app.kidsInSeoul.posts.repository;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.region.repository.Region;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long id;

    @Column(name = "title",length = 100,nullable = false)
    private String title;

    @Column(name = "content",columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "like_num")
    @ColumnDefault("0")
    private int likeNum;

    @Builder
    public Posts(String title, String content, Member member, Region region) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.region = region;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
