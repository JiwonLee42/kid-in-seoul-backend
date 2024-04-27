package app.kidsInSeoul.schedule.repository;

import app.kidsInSeoul.member.repository.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ScheduleRepository extends JpaRepository<Schedule,Long>  {
    @Query(value = "select s from Schedule s " +
            "where year(s.date) = :year " +
            "and month(s.date) = :month " +
            "and s.member = :member " )
    List<Schedule> findByMonth(@Param("year") int year, @Param("month") int month, @Param("member") Member member);

    @Query(value = "select s from Schedule s " +
            "where year(s.date) = :year " +
            "and month(s.date) = :month " +
            "and day(s.date) = :day " +
            "and s.member = :member " )
    List<Schedule> findByDay(@Param("year") int year, @Param("month") int month,@Param("day") int day ,@Param("member") Member member);
}
