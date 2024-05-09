package app.kidsInSeoul.schedule.repository;

import app.kidsInSeoul.facility.repository.*;
import app.kidsInSeoul.member.repository.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column(nullable = false)
    private LocalDate date;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    @Column(nullable = false)
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    @Column(nullable = false)
    private LocalTime endTime;

    @Column(name = "is_withchild")
    @Max(value = 1, message = "값은 1 이하여야 합니다.")
    private int isWithChild = 1;

    @Column(nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id", nullable = true)
    Facility facility;


    @Builder
    public Schedule(Member member, String title, String content,LocalDate date,LocalTime startTime,LocalTime endTime, Facility facility, int isWithChild, String type){
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

    public void update(String title,String content, LocalDate date, LocalTime startTime, LocalTime endTime, int isWithChild, String type, Facility facility){
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
