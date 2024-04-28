package app.kidsInSeoul.schedule.web.dto.response;

import app.kidsInSeoul.schedule.repository.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {
    private Long id;

    private String title;

    private String content;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private String facilityName;


    @Builder
    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.date = schedule.getDate();
        this.startTime = schedule.getStartTime();
        this.endTime = schedule.getEndTime();

        if(schedule.getFacility()!=null){
            this.facilityName = schedule.getFacility().getName();
        }

    }

}
