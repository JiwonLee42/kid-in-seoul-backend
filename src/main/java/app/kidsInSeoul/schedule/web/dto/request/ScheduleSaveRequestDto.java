package app.kidsInSeoul.schedule.web.dto.request;
import app.kidsInSeoul.facility.repository.*;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.schedule.repository.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ScheduleSaveRequestDto {

    private String title;

    private String content;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Member member;

    private boolean isWithChild;

    private String type;

    private Long kidscafeId;

    private Long libraryId;

    private Long parkId;

    private Long outdoorId;

    private Long artGalleryId;

    @Builder
    public ScheduleSaveRequestDto(String title, String content, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isWithChild,Member member,String type,Long artGalleryId, Long kidscafeId,Long parkId,Long libraryId, Long outdoorId){
        this.title = title;
        this.content = content;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.member = member;
        this.isWithChild = isWithChild;
        this.kidscafeId = kidscafeId;
        this.libraryId = libraryId;
        this.outdoorId = outdoorId;
        this.parkId = parkId;
        this.artGalleryId = artGalleryId;
        this.type = type;
    }


    public Schedule toEntity(Member member, KidsCafe kidsCafe, Library library, Park park, OutdoorFacility outdoorFacility, ArtGallery artGallery) {
        return Schedule.builder()
                .title(title)
                .content(content)
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .member(member)
                .kidsCafe(kidsCafe)
                .library(library)
                .park(park)
                .outdoorFacility(outdoorFacility)
                .artGallery(artGallery)
                .isWithChild(isWithChild)
                .type(type)
                .build();
    }

}
