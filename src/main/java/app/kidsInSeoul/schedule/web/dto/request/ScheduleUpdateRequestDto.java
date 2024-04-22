package app.kidsInSeoul.schedule.web.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class ScheduleUpdateRequestDto {
    String title;
    String content;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;

    @Builder
    public ScheduleUpdateRequestDto(String title, String content, LocalDate date, LocalTime startTime, LocalTime endTime){
        this.title = title;
        this.content = content;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
