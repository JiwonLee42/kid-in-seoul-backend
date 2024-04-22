package app.kidsInSeoul.schedule.web.response;

import app.kidsInSeoul.member.repository.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleResponseDto {
    private Long schedule_id;

    private String title;

    private String content;

    private LocalDateTime start_date;

    private LocalDateTime end_date;

    private Member member;


    @Builder
    public ScheduleResponseDto(Long schedule_id, String title, String content, LocalDateTime start_date, LocalDateTime end_date, Member member){
        this.schedule_id = schedule_id;
        this.title = title;
        this.content = content;
        this.start_date = start_date;
        this.end_date = end_date;
    }

}
