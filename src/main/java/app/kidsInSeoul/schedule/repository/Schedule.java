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
    @JoinColumn(name = "kids_cafe_id", nullable = true)
    private KidsCafe kidsCafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", nullable = true)
    private Library library;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outdoor_facility_id", nullable = true)
    private OutdoorFacility outdoorFacility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "park_id", nullable = true)
    private Park park;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_gallery_id", nullable = true)
    private ArtGallery artGallery;


    @Builder
    public Schedule(Member member, String title, String content,LocalDate date,LocalTime startTime,LocalTime endTime,ArtGallery artGallery,KidsCafe kidsCafe,Library library,Park park,OutdoorFacility outdoorFacility, boolean isWithChild, String type){
        this.member = member;
        this.title = title;
        this.content = content;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.artGallery = artGallery;
        this.kidsCafe = kidsCafe;
        this.library = library;
        this.outdoorFacility = outdoorFacility;
        this.isWithChild = isWithChild;
        this.type = type;
    }

    public void update(String title,String content, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isWithChild, String type){
        this.title = title;
        this.content = content;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isWithChild = isWithChild;
        this.type = type;
    }

}
