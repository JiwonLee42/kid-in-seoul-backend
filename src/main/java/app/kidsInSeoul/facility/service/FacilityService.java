package app.kidsInSeoul.facility.service;

import app.kidsInSeoul.common.exception.CustomException;
import app.kidsInSeoul.common.exception.ErrorCode;
import app.kidsInSeoul.facility.repository.*;
import app.kidsInSeoul.facility.web.dto.response.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FacilityService {

    private final ArtGalleryEduRepository artGalleryEduRepository;
    private final KidsCafeRepository kidsCafeRepository;
    private final LibraryRepository libraryRepository;
    private final OutdoorFacilityRepository outdoorFacilityRepository;
    private final ParkRepository parkRepository;

    public List<ArtGalleryEduResponseDto> getArtGalleryEduList() {
        List<ArtGalleryEdu> findAll = artGalleryEduRepository.findAll();
        return findAll.stream().map(ArtGalleryEduResponseDto::new).collect(Collectors.toList());
    }

    public ArtGalleryEduResponseDto getArtGalleryEdu(Long artGalleryEduId) {
        ArtGalleryEdu artGalleryEdu = artGalleryEduRepository.findById(artGalleryEduId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        return ArtGalleryEduResponseDto.builder()
                .id(artGalleryEdu.getId())
                .name(artGalleryEdu.getName())
                .target(artGalleryEdu.getTarget())
                .eduSpot(artGalleryEdu.getEduSpot())
                .eduStart(artGalleryEdu.getEduStart())
                .eduEnd(artGalleryEdu.getEduEnd())
                .content(artGalleryEdu.getContent())
                .eduLimit(artGalleryEdu.getEduLimit())
                .url(artGalleryEdu.getUrl())
                .eduFee(artGalleryEdu.getEduFee())
                .recruitStart(artGalleryEdu.getRecruitStart())
                .recruitEnd(artGalleryEdu.getRecruitEnd())
                .build();

    }

    public List<KidsCafeResponseDto> getKidsCafeList() {
        List<KidsCafe> findAll = kidsCafeRepository.findAll();
        return findAll.stream().map(KidsCafeResponseDto::new).collect(Collectors.toList());
    }

    public KidsCafeResponseDto getKidsCafe(Long kidsCafeId) {
        KidsCafe kidsCafe = kidsCafeRepository.findById(kidsCafeId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

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

    public List<LibraryResponseDto> getLibraryList() {
        List<Library> findAll = libraryRepository.findAll();
        return findAll.stream().map(LibraryResponseDto::new).collect(Collectors.toList());
    }

    public LibraryResponseDto getLibrary(Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        return LibraryResponseDto.builder()
                .id(library.getId())
                .name(library.getName())
                .phoneNum(library.getPhoneNum())
                .homepageUrl(library.getHomepageUrl())
                .latitude(library.getLatitude())
                .longitude(library.getLongitude())
                .operatingTime(library.getOperatingTime())
                .regionGu(library.getRegionGu())
                .street(library.getStreet())
                .postNum(library.getPostNum())
                .build();
    }


    public List<OutdoorFacilityResponseDto> getOutdoorFacilityList() {
        List<OutdoorFacility> findAll = outdoorFacilityRepository.findAll();
        return findAll.stream().map(OutdoorFacilityResponseDto::new).collect(Collectors.toList());
    }

    public OutdoorFacilityResponseDto getOutdoorFacility(Long outdoorFacilityId) {
        OutdoorFacility outdoorFacility = outdoorFacilityRepository.findById(outdoorFacilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        return OutdoorFacilityResponseDto.builder()
                .id(outdoorFacility.getId())
                .name(outdoorFacility.getName())
                .address(outdoorFacility.getAddress())
                .regionGu(outdoorFacility.getRegionGu())
                .ageClassification(outdoorFacility.getAgeClassification())
                .latitude(outdoorFacility.getLatitude())
                .longitude(outdoorFacility.getLongitude())
                .detailAddress(outdoorFacility.getDetailAddress())
                .postNum(outdoorFacility.getPostNum())
                .free(outdoorFacility.isFree())
                .fee(outdoorFacility.getFee())
                .urlLink(outdoorFacility.getUrlLink())
                .build();
    }


    public List<ParkResponseDto> getParkList() {
        List<Park> findAll = parkRepository.findAll();
        return findAll.stream().map(ParkResponseDto::new).collect(Collectors.toList());
    }

    public ParkResponseDto getPark(Long parkId) {
        Park park = parkRepository.findById(parkId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        return ParkResponseDto.builder()
                .id(park.getId())
                .name(park.getName())
                .regionSi(park.getRegionSi())
                .regionGu(park.getRegionGu())
                .regionDong(park.getRegionDong())
                .address(park.getAddress())
                .mainCategory(park.getMainCategory())
                .callNumber(park.getCallNumber())
                .build();
    }
}
