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
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@RestController
public class ScheduleApiController {

    private final ScheduleService scheduleService;


    // 스케줄 등록
    @PostMapping("/add")
    public ResponseEntity<Long> save(@RequestBody ScheduleSaveRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long scheduleId = scheduleService.save(requestDto, userDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body(scheduleId);
    }


    // 월별 스케줄 조회
    @GetMapping("/view-month")
    public ResponseEntity<List<ScheduleResponseDto>> findByMonth(@RequestParam int year, @RequestParam int month, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<ScheduleResponseDto> responseDto = scheduleService.findByMonth(year,month,userDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 일별 스케줄 조회
    @GetMapping("/view-day")
    public ResponseEntity<List<ScheduleResponseDto>> findByDay(@RequestParam int year, @RequestParam int month,@RequestParam int day, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<ScheduleResponseDto> responseDto = scheduleService.findByDay(year,month,day,userDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


    // 스케줄 삭제
    @DeleteMapping("/delete/{scheduleId}")
    public ResponseEntity<String> deleteById(@PathVariable Long scheduleId, @AuthenticationPrincipal CustomUserDetails userDetails){
        scheduleService.deleteById(scheduleId,userDetails.getMember());
        System.out.println(userDetails.getMember().getUserId());
        return ResponseEntity.ok("스케줄이 성공적으로 삭제되었습니다.");
    }

    // 스케줄 수정
    @PutMapping("/edit/{scheduleId}")
    public ResponseEntity<String> edit(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        scheduleService.update(scheduleId,requestDto,userDetails.getMember());
        System.out.println("업데이트: " + requestDto.toString());
        return ResponseEntity.ok("스케줄이 성공적으로 수정되었습니다.");
    }

    // 아이와 함께한 시간 조회

    @GetMapping("/view/with-child")
    public ResponseEntity<List<Map<String,String>>> findTimeWithChild(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<Map<String,String>> result = scheduleService.findTimeChild(userDetails.getMember());
        return ResponseEntity.ok(result);
    }




}
