package app.kidsInSeoul.facility.service;

import app.kidsInSeoul.facility.repository.*;
import app.kidsInSeoul.facility.web.dto.response.KidsCafeResponseDto;
import app.kidsInSeoul.facility.web.dto.response.LibraryResponseDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FacilityService {

    private final KidsCafeRepository kidsCafeRepository;
    private final LibraryRepository libraryRepository;
    private final OutdoorFacilityRepository outdoorFacilityRepository;
    private final ParkRepository parkRepository;


    public KidsCafeResponseDto getKidsCafe(Long kidsCafeId) {
        KidsCafe kidsCafe = kidsCafeRepository.findById(kidsCafeId).orElseThrow(() -> new IllegalArgumentException("해당 id의 시설이 존재하지 않습니다."));

        return KidsCafeResponseDto.builder()
                .id(kidsCafe.getId())
                .name(kidsCafe.getName())
                .address(kidsCafe.getAddress())
                .latitude(kidsCafe.getLatitude())
                .longitude(kidsCafe.getLongitude())
                .phoneNum(kidsCafe.getPhoneNum())
                .regionGu(kidsCafe.getRegionGu())
                .regionDong(kidsCafe.getRegionDong())
                .availableAge(kidsCafe.getAvailableAge())
                .closedDays(kidsCafe.getClosedDays())
                .operatingDays(kidsCafe.getOperatingDays())
                .usageCapacity(kidsCafe.getUsageCapacity())
                .build();
    }

    /*
    public LibraryResponseDto getLibrary(Long libraryId) {

    }

     */
}
