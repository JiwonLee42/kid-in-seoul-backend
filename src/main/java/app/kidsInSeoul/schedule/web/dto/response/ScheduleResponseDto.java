package app.kidsInSeoul.schedule.web.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {
    private Long schedule_id;

    private String title;

    private String content;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;


    @Builder
    public ScheduleResponseDto(Long schedule_id, String title, String content,LocalDate date, LocalTime startTime, LocalTime endTime){
        this.schedule_id = schedule_id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
