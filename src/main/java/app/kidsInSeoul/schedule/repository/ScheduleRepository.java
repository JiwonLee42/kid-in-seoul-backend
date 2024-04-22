package app.kidsInSeoul.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> , QuerydslPredicateExecutor<Schedule> {
}
