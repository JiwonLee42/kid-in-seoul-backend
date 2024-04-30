package app.kidsInSeoul.facility.service;

import app.kidsInSeoul.common.exception.CustomException;
import app.kidsInSeoul.common.exception.ErrorCode;
import app.kidsInSeoul.facility.repository.*;
import app.kidsInSeoul.facility.web.FacilityController;
import app.kidsInSeoul.facility.web.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FacilityService {

    private final ArtGalleryRepository artGalleryRepository;
    private final KidsCafeRepository kidsCafeRepository;
    private final LibraryRepository libraryRepository;
    private final OutdoorFacilityRepository outdoorFacilityRepository;
    private final ParkRepository parkRepository;

    public List<ArtGalleryResponseDto> getArtGalleryList() {
        List<ArtGallery> findAll = artGalleryRepository.findAll();
        return findAll.stream().map(ArtGalleryResponseDto::new).collect(Collectors.toList());
    }

    public ArtGalleryResponseDto getArtGallery(Long facilityId) {
        ArtGallery artGallery = artGalleryRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        return ArtGalleryResponseDto.builder()
                .id(artGallery.getId())
                .name(artGallery.getName())
                .url(artGallery.getUrl())
                .phoneNum(artGallery.getPhoneNum())
                .address(artGallery.getAddress())
                .adultFee(artGallery.getAdultFee())
                .childFee(artGallery.getChildFee())
                .build();

    }

    public List<KidsCafeResponseDto> getKidsCafeList() {
        List<KidsCafe> findAll = kidsCafeRepository.findAll();
        return findAll.stream().map(KidsCafeResponseDto::new).collect(Collectors.toList());
    }

    public KidsCafeResponseDto getKidsCafe(Long facilityId) {
        KidsCafe kidsCafe = kidsCafeRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

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

    public LibraryResponseDto getLibrary(Long facilityId) {
        Library library = libraryRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

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

    public OutdoorFacilityResponseDto getOutdoorFacility(Long facilityId) {
        OutdoorFacility outdoorFacility = outdoorFacilityRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

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

    public ParkResponseDto getPark(Long facilityId) {
        Park park = parkRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

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

    public List<FacilityResponseDto> getAllFacilities() {

        List<ArtGallery> findArtGalleries = artGalleryRepository.findAll();
        List<KidsCafe> findKidsCafes = kidsCafeRepository.findAll();
        List<Library> findLibraries = libraryRepository.findAll();
        List<OutdoorFacility> findOutdoorFacilities = outdoorFacilityRepository.findAll();
        List<Park> findParks = parkRepository.findAll();

        List<FacilityResponseDto> result = new ArrayList<>();

        for (ArtGallery o: findArtGalleries) {
            result.add(FacilityResponseDto.builder()
                    .id(o.getId())
                    .name(o.getName())
                    .type("ART_GALLERY")
                    .build());
        }

        for (KidsCafe o: findKidsCafes) {
            result.add(FacilityResponseDto.builder()
                    .id(o.getId())
                    .name(o.getName())
                    .type("KIDS_CAFE")
                    .build());
        }

        for (Library o: findLibraries) {
            result.add(FacilityResponseDto.builder()
                    .id(o.getId())
                    .name(o.getName())
                    .type("LIBRARY")
                    .build());
        }

        for (OutdoorFacility o: findOutdoorFacilities) {
            result.add(FacilityResponseDto.builder()
                    .id(o.getId())
                    .name(o.getName())
                    .type("OUTDOOR")
                    .build());
        }

        for (Park o: findParks) {
            result.add(FacilityResponseDto.builder()
                    .id(o.getId())
                    .name(o.getName())
                    .type("PARK")
                    .build());
        }

        return result;
    }
}
