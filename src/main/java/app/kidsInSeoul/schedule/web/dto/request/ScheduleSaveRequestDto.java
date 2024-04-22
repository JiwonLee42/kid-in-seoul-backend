package app.kidsInSeoul.schedule.web.request;


import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.schedule.repository.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleSaveRequestDto {

    private String title;

    private String content;

    private LocalDateTime start_date;

    private LocalDateTime end_date;

    private Member member;

    @Builder
    public ScheduleSaveRequestDto(String title, String content, LocalDateTime start_date, LocalDateTime end_date, Member member){
        this.title = title;
        this.content = content;
        this.start_date = start_date;
        this.end_date = end_date;
        this.member = member;
    }

    public Schedule toEntity(Member member) {
        return Schedule.builder()
                .title(title)
                .content(content)
                .start_date(start_date)
                .end_date(end_date)
                .member(member)
                .build();
    }
}
