package app.kidsInSeoul.event.web.dto.response;

import app.kidsInSeoul.event.repository.Events;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EventResponseDto {

    private Long id;
    private String eventName;

    private String place;

    private String startDate;

    private String endDate;

    private String content;

    @Builder
    public EventResponseDto(Events event) {
        this.id = event.getId();
        this.eventName = event.getEventName();
        this.place = event.getPlace();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.content = event.getContent();
    }
}
