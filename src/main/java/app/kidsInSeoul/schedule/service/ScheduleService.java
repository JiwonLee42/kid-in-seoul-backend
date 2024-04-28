package app.kidsInSeoul.schedule.service;
import app.kidsInSeoul.facility.repository.*;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.schedule.repository.Schedule;
import app.kidsInSeoul.schedule.repository.ScheduleRepository;
import app.kidsInSeoul.schedule.web.dto.request.ScheduleSaveRequestDto;
import app.kidsInSeoul.schedule.web.dto.request.ScheduleUpdateRequestDto;
import app.kidsInSeoul.schedule.web.dto.response.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final KidsCafeRepository kidsCafeRepository;

    private final OutdoorFacilityRepository outdoorFacilityRepository;

    private final ParkRepository parkRepository;

    private final LibraryRepository libraryRepository;


    @Transactional
    public Long save(ScheduleSaveRequestDto requestDto, Member member){
        Facility facility = null;
        if (requestDto.getFacilityId() != null) {
            facility = kidsCafeRepository.findById(requestDto.getFacilityId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 시설이 없습니다."));
        }

        return scheduleRepository.save(requestDto.toEntity(member, facility)).getId();

    }


    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findByMonth(int year, int month, Member member) {
        List<Schedule> schedules = scheduleRepository.findByMonth(year,month,member);
        return schedules.stream().map(b -> new ScheduleResponseDto(b)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findByDay(int year, int month, int day, Member member) {
        List<Schedule> schedules = scheduleRepository.findByDay(year,month,day,member);
        return schedules.stream().map(b -> new ScheduleResponseDto(b)).collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id,Member currentUser) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 스케줄이 없습니다. id=" + id));
        if(!schedule.getMember().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"스케줄 작성자가 아닙니다.");
        }
        scheduleRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, ScheduleUpdateRequestDto requestDto,Member currentUser) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 스케줄이 없습니다. id = " + id));
        if(!schedule.getMember().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"스케줄 작성자가 아닙니다.");
        }
        schedule.update(requestDto.getTitle(),requestDto.getContent(),requestDto.getDate(),requestDto.getStartTime(),requestDto.getEndTime(), requestDto.isWithChild(),requestDto.getType());
    }


}
