package app.kidsInSeoul.schedule.repository;

import app.kidsInSeoul.member.repository.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime start_date;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime end_date;

    // 도서관, 공원, 키즈카페, 지역보육 야외시설 외래키 추가하기

    @Builder
    public Schedule(Member member, String title, String content, LocalDateTime start_date, LocalDateTime end_date){
        this.member = member;
        this.title = title;
        this.content = content;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}