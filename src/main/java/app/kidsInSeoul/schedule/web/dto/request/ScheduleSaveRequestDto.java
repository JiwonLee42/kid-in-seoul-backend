package app.kidsInSeoul.schedule.web.dto.request;
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

    @Builder
    public ScheduleSaveRequestDto(String title, String content, LocalDate date, LocalTime startTime,LocalTime endTime, Member member){
        this.title = title;
        this.content = content;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.member = member;
    }

    public Schedule toEntity(Member member) {
        return Schedule.builder()
                .title(title)
                .content(content)
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .member(member)
                .build();
    }

}
