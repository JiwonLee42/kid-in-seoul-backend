package app.kidsInSeoul.facility.web;

import app.kidsInSeoul.facility.service.FacilityService;
import app.kidsInSeoul.facility.web.dto.response.KidsCafeResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController("/facility")
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping("/kids-cafe/{kidsCafeId}")
    public ResponseEntity<KidsCafeResponseDto> getKidsCafe(@PathVariable Long kidsCafeId) {

        KidsCafeResponseDto kidsCafeResponseDto = facilityService.getKidsCafe(kidsCafeId);

        return ResponseEntity.status(HttpStatus.OK).body(kidsCafeResponseDto);
    }


}
