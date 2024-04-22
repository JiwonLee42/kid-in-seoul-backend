package app.kidsInSeoul.schedule.service;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.schedule.repository.QSchedule;
import app.kidsInSeoul.schedule.repository.Schedule;
import app.kidsInSeoul.schedule.repository.ScheduleRepository;
import app.kidsInSeoul.schedule.web.request.ScheduleSaveRequestDto;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<Schedule> findByMonth(@PathVariable int month) {
        QSchedule qSchedule = QSchedule.schedule;
        NumberExpression<Integer> this_month = qSchedule.start_date.month();
        return queryFactory
                .selectFrom(qSchedule)
                .where(this_month.eq(month))
                .fetch();
    }



}
