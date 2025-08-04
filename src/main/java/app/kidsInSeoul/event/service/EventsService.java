package app.kidsInSeoul.event.service;

import app.kidsInSeoul.common.exception.CustomException;
import app.kidsInSeoul.common.exception.ErrorCode;
import app.kidsInSeoul.event.repository.Events;
import app.kidsInSeoul.event.repository.EventsRepository;
import app.kidsInSeoul.event.web.dto.response.EventResponseDto;
import app.kidsInSeoul.kindergarden.repository.Kindergarden;
import app.kidsInSeoul.kindergarden.web.dto.response.KindergardenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EventsService {

    private final EventsRepository eventsRepository;

    @Transactional
    public EventResponseDto findById(Long id) {
        Events event = eventsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "행사를 찾을 수 없습니다."));
        return new EventResponseDto(event);
    }

    @Transactional
    public List<EventResponseDto> findAll() {
        List<Events> events = eventsRepository.findAll();
        return events.stream().map(b -> new EventResponseDto(b)).collect(Collectors.toList());
    }
}
