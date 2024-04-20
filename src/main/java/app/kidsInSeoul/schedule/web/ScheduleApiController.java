package app.kidsInSeoul.schedule.web;


import app.kidsInSeoul.jwt.service.dto.CustomUserDetails;
import app.kidsInSeoul.schedule.service.ScheduleService;
import app.kidsInSeoul.schedule.web.request.ScheduleSaveRequestDto;
import app.kidsInSeoul.schedule.web.response.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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


    /*
    // 월별 스케줄 조회
    @GetMapping("/schedule/view/{month}")
    public ResponseEntity<ScheduleResponseDto> findByMonth(@PathVariable int month,@AuthenticationPrincipal CustomUserDetails userDetails) {

    }
    */

    // 일별 스케줄 조회

    // 스케줄 삭제

    // 스케줄 수정


}
