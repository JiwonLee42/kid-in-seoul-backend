package app.kidsInSeoul.schedule.web;
import app.kidsInSeoul.jwt.service.dto.CustomUserDetails;
import app.kidsInSeoul.schedule.service.ScheduleService;
import app.kidsInSeoul.schedule.web.dto.request.ScheduleSaveRequestDto;
import app.kidsInSeoul.schedule.web.dto.request.ScheduleUpdateRequestDto;
import app.kidsInSeoul.schedule.web.dto.response.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScheduleApiController {

    ScheduleService scheduleService;


    // 스케줄 등록
    @PostMapping("/schedule/add")
    public ResponseEntity<Long> save(@RequestBody ScheduleSaveRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long scheduleId = scheduleService.save(requestDto, userDetails.getMember());
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleId);
    }


    // 월별 스케줄 조회
    @GetMapping("/schedule/view-month")
    public ResponseEntity<List<ScheduleResponseDto>> findByMonth(@RequestParam int year, @RequestParam int month, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<ScheduleResponseDto> responseDto = scheduleService.findByMonth(year,month,userDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 일별 스케줄 조회
    @GetMapping("/schedule/view-day")
    public ResponseEntity<List<ScheduleResponseDto>> findByDay(@RequestParam int year, @RequestParam int month,@RequestParam int day, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<ScheduleResponseDto> responseDto = scheduleService.findByDay(year,month,day,userDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


    // 스케줄 삭제
    @DeleteMapping("/schedule/delete/{schedule_id}")
    public ResponseEntity<String> deleteById(@PathVariable Long scheduleId, @AuthenticationPrincipal CustomUserDetails userDetails){
        scheduleService.deleteById(scheduleId,userDetails.getMember());
        return ResponseEntity.ok("스케줄이 성공적으로 삭제되었습니다.");
    }

    // 스케줄 수정
    @PutMapping("/schedule/edit/{schedule_id}")
    public ResponseEntity<String> edit(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        scheduleService.update(scheduleId,requestDto,userDetails.getMember());
        return ResponseEntity.ok("스케줄이 성공적으로 수정되었습니다.");
    }



}
