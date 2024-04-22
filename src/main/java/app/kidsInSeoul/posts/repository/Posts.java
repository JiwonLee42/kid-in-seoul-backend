package app.kidsInSeoul.posts.repository;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.region.repository.Region;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column(name = "title",length = 100,nullable = false)
    private String title;

    @Column(name = "content",columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Builder
    public Posts(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.region = member != null ? member.getRegion() : null; // Member 객체가 존재하면 해당 멤버의 지역구를 가져옴
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
