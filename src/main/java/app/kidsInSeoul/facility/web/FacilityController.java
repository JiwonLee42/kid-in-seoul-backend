package app.kidsInSeoul.facility.web;

import app.kidsInSeoul.facility.service.FacilityService;
import app.kidsInSeoul.facility.web.dto.response.*;
import app.kidsInSeoul.jwt.service.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController("/facility")
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping("/art-gallery/list")
    public ResponseEntity<List<ArtGalleryResponseDto>> getArtGallery() {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getArtGalleryList());
    }

    @GetMapping("/art-gallery/{artGalleryEduId}")
    public ResponseEntity<ArtGalleryResponseDto> getArtGallery(@AuthenticationPrincipal CustomUserDetails userDetail, @PathVariable Long artGalleryEduId) {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getArtGallery(userDetail.getMember(), artGalleryEduId));
    }

    @GetMapping("/kids-cafe/list")
    public ResponseEntity<List<KidsCafeResponseDto>> getKidsCafeList() {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getKidsCafeList());
    }

    @GetMapping("/kids-cafe/{kidsCafeId}")
    public ResponseEntity<KidsCafeResponseDto> getKidsCafe(@AuthenticationPrincipal CustomUserDetails userDetail, @PathVariable Long kidsCafeId) {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getKidsCafe(userDetail.getMember(), kidsCafeId));
    }

    @GetMapping("/library/list")
    public ResponseEntity<List<LibraryResponseDto>> getLibraryList() {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getLibraryList());
    }

    @GetMapping("library/{libraryId}")
    public ResponseEntity<LibraryResponseDto> getLibrary(@AuthenticationPrincipal CustomUserDetails userDetail, @PathVariable Long libraryId) {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getLibrary(userDetail.getMember(), libraryId));
    }

    @GetMapping("/outdoor-facility/list")
    public ResponseEntity<List<OutdoorFacilityResponseDto>> getOutdoorFacilityList() {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getOutdoorFacilityList());
    }

    @GetMapping("/outdoor-facility/{outdoorFacilityId}")
    public ResponseEntity<OutdoorFacilityResponseDto> getOutdoorFacility(@AuthenticationPrincipal CustomUserDetails userDetail, @PathVariable Long outdoorFacilityId) {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getOutdoorFacility(userDetail.getMember(), outdoorFacilityId));
    }

    @GetMapping("/park/list")
    public ResponseEntity<List<ParkResponseDto>> getParkList() {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getParkList());
    }

    @GetMapping("/park/{parkId}")
    public ResponseEntity<ParkResponseDto> getPark(@AuthenticationPrincipal CustomUserDetails userDetail, @PathVariable Long parkId) {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getPark(userDetail.getMember(), parkId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<FacilityResponseDto>> getAllFacilities() {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getAllFacilities());
    }

}
