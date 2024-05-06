package app.kidsInSeoul.kindergarden.web.dto;


import app.kidsInSeoul.jwt.service.dto.CustomUserDetails;
import app.kidsInSeoul.kindergarden.repository.Kindergarden;
import app.kidsInSeoul.kindergarden.service.KindergardenService;
import app.kidsInSeoul.kindergarden.web.dto.response.KindergardenResponseDto;
import app.kidsInSeoul.posts.web.dto.response.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/kindergarden")
public class KindergardenController {

    private final KindergardenService kindergardenService;

    @GetMapping("/view-region")
    public ResponseEntity<List<KindergardenResponseDto>> findByRegionId(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<KindergardenResponseDto> kindergardens = kindergardenService.findByRegionId(userDetails.getMember().getRegion().getId());
        return ResponseEntity.status(HttpStatus.OK).body(kindergardens);
    }

    @GetMapping("/view-region/night")
    public ResponseEntity<List<KindergardenResponseDto>> findByRegionIdOpNight(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<KindergardenResponseDto> kindergardens = kindergardenService.findNightOp(userDetails.getMember().getRegion().getId());
        return ResponseEntity.status(HttpStatus.OK).body(kindergardens);
    }

    @GetMapping("/view-all")
    public ResponseEntity<List<KindergardenResponseDto>> findAll() {
        List<KindergardenResponseDto> kindergardens = kindergardenService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(kindergardens);
    }


}
