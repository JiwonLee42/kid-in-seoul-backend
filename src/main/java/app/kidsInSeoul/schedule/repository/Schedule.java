package app.kidsInSeoul.schedule.repository;

import app.kidsInSeoul.facility.repository.*;
import app.kidsInSeoul.member.repository.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name ="kids_cafe_id", nullable = true)
    private KidsCafe kidsCafe;

    @ManyToOne
    @JoinColumn(name = "library_id", nullable = true)
    private Library library;

    @ManyToOne
    @JoinColumn(name = "park", nullable = true)
    private Park park;

    @ManyToOne
    @JoinColumn(name = "arteducation_id", nullable = true)
    private ArtEducation artEducation;

    @ManyToOne
    @JoinColumn(name = "outdoorfacility_id",nullable = true)
    private OutdoorFacility outdoorFacility;


    @Builder
    public Schedule(Member member, String title, String content,LocalDate date,LocalTime startTime,LocalTime endTime,KidsCafe kidsCafe,Library library, Park park, ArtEducation artEducation,OutdoorFacility outdoorFacility){
        this.member = member;
        this.title = title;
        this.content = content;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.kidsCafe = kidsCafe;
        this.library = library;
        this.park = park;
        this.artEducation = artEducation;
        this.outdoorFacility = outdoorFacility;
    }

    public void update(String title,String content, LocalDate date, LocalTime startTime, LocalTime endTime){
        this.title = title;
        this.content = content;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
