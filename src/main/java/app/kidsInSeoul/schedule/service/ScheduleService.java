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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final KidsCafeRepository kidsCafeRepository;

    private final OutdoorFacilityRepository outdoorFacilityRepository;

    private final ParkRepository parkRepository;

    private final LibraryRepository libraryRepository;

    private final ArtGalleryRepository artGalleryRepository;

    private final FacilityRepository facilityRepository;


    @Transactional
    public Long save(ScheduleSaveRequestDto requestDto, Member member){

        Facility facility = facilityRepository.findById(requestDto.getFacilityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 시설이 없습니다."));

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
    public void deleteById(Long id, Member currentUser) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 스케줄이 없습니다. id=" + id));
        if(!schedule.getMember().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"스케줄 작성자가 아닙니다.");
        }
        scheduleRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, ScheduleUpdateRequestDto requestDto, Member currentUser) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 스케줄이 없습니다. id = " + id));
        if(!schedule.getMember().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"스케줄 작성자가 아닙니다.");
        }

        Facility facility = facilityRepository.findById(requestDto.getFacilityId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 시설이 없습니다."));


        schedule.update(requestDto.getTitle(),requestDto.getContent(),requestDto.getDate(),requestDto.getStartTime(),requestDto.getEndTime(), requestDto.getIsWithChild(),requestDto.getType(), facility);
    }

    @Transactional
    public List<Map<String, String>> findTimeChild(Member currentUser) {
        List<Map<String, String>> resultList = getDateTimeInfo(currentUser);
        return resultList;
    }

    public List<Map<String, String>> getDateTimeInfo(Member member) {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(6);
        System.out.println("시간 출력!" + endDate.toString() + "~" + startDate.toString());

        List<Object[]> dateTimeInfo = scheduleRepository.getDateTimeInfo(startDate, endDate, member);
        List<Integer> timesWithChild = findTimeWithChild(dateTimeInfo);

        Map<DayOfWeek, Integer> dayTimeMap = calculateDayTimeMap(dateTimeInfo, timesWithChild);

        List<Map<String, String>> result = createResultList(endDate, dayTimeMap);

        return result;
    }

    private Map<DayOfWeek, Integer> calculateDayTimeMap(List<Object[]> dateTimeInfo, List<Integer> timesWithChild) {
        Map<DayOfWeek, Integer> dayTimeMap = new HashMap<>();
        for (int i = 0; i < dateTimeInfo.size(); i++) {
            LocalTime startTime = (LocalTime) dateTimeInfo.get(i)[0];
            LocalTime endTime = (LocalTime) dateTimeInfo.get(i)[1];
            LocalDate date = (LocalDate) dateTimeInfo.get(i)[2];
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            int time = timesWithChild.get(i);
            dayTimeMap.put(dayOfWeek, dayTimeMap.getOrDefault(dayOfWeek, 0) + time);
        }
        return dayTimeMap;
    }

    private List<Map<String, String>> createResultList(LocalDate endDate, Map<DayOfWeek, Integer> dayTimeMap) {
        List<Map<String, String>> result = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDate date = endDate.minusDays(i);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            String day = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);
            int time = dayTimeMap.getOrDefault(dayOfWeek, 0);
            Map<String, String> entry = new HashMap<>();
            entry.put("day", day);
            entry.put("time", Integer.toString(time));
            result.add(entry);
        }
        return result;
    }

    private List<Integer> findTimeWithChild(List<Object[]> dateTimeInfo) {
        List<Integer> timesWithChild = new ArrayList<>();
        for (Object[] data : dateTimeInfo) {
            LocalTime startTime = (LocalTime) data[0];
            LocalTime endTime = (LocalTime) data[1];
            long totalMinutes = ChronoUnit.MINUTES.between(startTime, endTime);
            int totalHours = Math.round(totalMinutes / 60.0f);
            timesWithChild.add(totalHours);
        }
        System.out.println("중간 점검 로그" + timesWithChild);
        return timesWithChild;
    }

}
