package app.kidsInSeoul.event.web;


import app.kidsInSeoul.event.service.EventsService;
import app.kidsInSeoul.event.web.dto.response.EventResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
public class EventsController {

    private final EventsService eventsService;

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDto> findById(@PathVariable Long eventId) {
        EventResponseDto responseDto = eventsService.findById(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventResponseDto>> findAll() {
        List<EventResponseDto> responseDto = eventsService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
