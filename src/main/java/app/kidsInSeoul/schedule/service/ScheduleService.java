package app.kidsInSeoul.schedule.service;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.repository.Posts;
import app.kidsInSeoul.schedule.repository.QSchedule;
import app.kidsInSeoul.schedule.repository.Schedule;
import app.kidsInSeoul.schedule.repository.ScheduleRepository;
import app.kidsInSeoul.schedule.web.dto.request.ScheduleSaveRequestDto;
import app.kidsInSeoul.schedule.web.dto.request.ScheduleUpdateRequestDto;
import app.kidsInSeoul.schedule.web.dto.response.ScheduleResponseDto;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Transactional
    public Long save(ScheduleSaveRequestDto requestDto, Member member){
        return scheduleRepository.save(requestDto.toEntity(member))
                .getSchedule_id();
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findByMonth(int year, int month, Member member) {
        QSchedule qSchedule = QSchedule.schedule;
        NumberExpression<Integer> this_month = qSchedule.date.month();
        NumberExpression<Integer> this_year = qSchedule.date.year();
        NumberExpression<Integer> this_day = qSchedule.date.dayOfMonth();

        List<ScheduleResponseDto> scheduleDtoList = new ArrayList<>();

        List<Schedule>  schedules = queryFactory
                .selectFrom(qSchedule)
                .where(qSchedule.date.year().eq(year)
                        .and(qSchedule.date.month().eq(month))
                        .and(qSchedule.member.eq(member)))
                .fetch();

        for(Schedule schedule : schedules) {
            ScheduleResponseDto dto = ScheduleResponseDto.builder()
                    .schedule_id(schedule.getSchedule_id())
                    .title(schedule.getTitle())
                    .content(schedule.getContent())
                    .date(schedule.getDate())
                    .startTime(schedule.getStartTime())
                    .endTime(schedule.getEndTime())
                    .build();
            scheduleDtoList.add(dto);
        }

        return scheduleDtoList;
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findByDay(int year, int month, int day, Member member) {
        QSchedule qSchedule = QSchedule.schedule;
        NumberExpression<Integer> this_month = qSchedule.date.month();
        NumberExpression<Integer> this_year = qSchedule.date.year();
        NumberExpression<Integer> this_day = qSchedule.date.dayOfMonth();

        List<ScheduleResponseDto> scheduleDtoList = new ArrayList<>();

        List<Schedule>  schedules = queryFactory
                .selectFrom(qSchedule)
                .where(qSchedule.date.year().eq(year)
                        .and(qSchedule.date.month().eq(month))
                        .and(qSchedule.date.dayOfMonth().eq(day))
                        .and(qSchedule.member.eq(member)))
                .fetch();

        for(Schedule schedule : schedules) {
            ScheduleResponseDto dto = ScheduleResponseDto.builder()
                    .schedule_id(schedule.getSchedule_id())
                    .title(schedule.getTitle())
                    .content(schedule.getContent())
                    .date(schedule.getDate())
                    .startTime(schedule.getStartTime())
                    .endTime(schedule.getEndTime())
                    .build();
            scheduleDtoList.add(dto);
        }

        return scheduleDtoList;
    }

    @Transactional
    public void deleteById(Long id,Member currentUser) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 스케줄이 없습니다. id=" + id));
        if(!schedule.getMember().equals(currentUser)) {
            throw  new ResponseStatusException(HttpStatus.FORBIDDEN,"스케줄 작성자가 아닙니다.");
        }

        scheduleRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, ScheduleUpdateRequestDto requestDto,Member currentUser) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 스케줄이 없습니다. id = " + id));
        if(!schedule.getMember().equals(currentUser)) {
            throw  new ResponseStatusException(HttpStatus.FORBIDDEN,"스케줄 작성자가 아닙니다.");
        }
        schedule.update(requestDto.getTitle(),requestDto.getContent(),requestDto.getDate(),requestDto.getStartTime(),requestDto.getEndTime());
    }


}
