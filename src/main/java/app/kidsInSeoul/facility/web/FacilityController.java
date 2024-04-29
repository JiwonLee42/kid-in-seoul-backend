package app.kidsInSeoul.facility.web;

import app.kidsInSeoul.facility.service.FacilityService;
import app.kidsInSeoul.facility.web.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController("/facility")
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping("/art-gallery-edu/list")
    public ResponseEntity<List<ArtGalleryResponseDto>> getArtGalleryEdu() {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getArtGalleryEduList());
    }

    @GetMapping("/art-gallery-edu/{artGalleryEduId}")
    public ResponseEntity<ArtGalleryResponseDto> getArtGalleryEdu(@PathVariable Long artGalleryEduId) {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getArtGalleryEdu(artGalleryEduId));
    }

    @GetMapping("/kids-cafe/list")
    public ResponseEntity<List<KidsCafeResponseDto>> getKidsCafeList() {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getKidsCafeList());
    }

    @GetMapping("/kids-cafe/{kidsCafeId}")
    public ResponseEntity<KidsCafeResponseDto> getKidsCafe(@PathVariable Long kidsCafeId) {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getKidsCafe(kidsCafeId));
    }

    @GetMapping("/library/list")
    public ResponseEntity<List<LibraryResponseDto>> getLibraryList() {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getLibraryList());
    }

    @GetMapping("library/{libraryId}")
    public ResponseEntity<LibraryResponseDto> getLibrary(@PathVariable Long libraryId) {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getLibrary(libraryId));
    }

    @GetMapping("/outdoor-facility/list")
    public ResponseEntity<List<OutdoorFacilityResponseDto>> getOutdoorFacilityList() {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getOutdoorFacilityList());
    }

    @GetMapping("/outdoor-facility/{outdoorFacilityId}")
    public ResponseEntity<OutdoorFacilityResponseDto> getOutdoorFacility(@PathVariable Long outdoorFacilityId) {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getOutdoorFacility(outdoorFacilityId));
    }

    @GetMapping("/park/list")
    public ResponseEntity<List<ParkResponseDto>> getParkList() {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getParkList());
    }

    @GetMapping("/park/{parkId}")
    public ResponseEntity<ParkResponseDto> getPark(@PathVariable Long parkId) {
        return ResponseEntity.status(HttpStatus.OK).body(facilityService.getPark(parkId));
    }

}
