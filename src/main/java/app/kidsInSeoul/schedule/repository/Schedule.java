package app.kidsInSeoul.schedule.repository;

import app.kidsInSeoul.facility.repository.*;
import app.kidsInSeoul.member.repository.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity
public class Schedule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(name = "is_withchild")
    private boolean isWithChild;

    @Column(nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id", nullable = true)
    Facility facility;


    @Builder
    public Schedule(Member member, String title, String content,LocalDate date,LocalTime startTime,LocalTime endTime, Facility facility, boolean isWithChild, String type){
        this.member = member;
        this.title = title;
        this.content = content;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.facility = facility;
        this.isWithChild = isWithChild;
        this.type = type;
    }

    public void update(String title,String content, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isWithChild, String type, Facility facility){
        this.title = title;
        this.content = content;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isWithChild = isWithChild;
        this.type = type;
        this.facility = facility;
    }

}
