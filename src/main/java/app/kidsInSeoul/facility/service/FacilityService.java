package app.kidsInSeoul.facility.service;

import app.kidsInSeoul.common.exception.CustomException;
import app.kidsInSeoul.common.exception.ErrorCode;
import app.kidsInSeoul.facility.repository.*;
import app.kidsInSeoul.facility.web.FacilityController;
import app.kidsInSeoul.facility.web.dto.response.*;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.member_preferred_facility.repository.MemberPreferredFacilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FacilityService {

    private final ArtGalleryRepository artGalleryRepository;
    private final KidsCafeRepository kidsCafeRepository;
    private final LibraryRepository libraryRepository;
    private final OutdoorFacilityRepository outdoorFacilityRepository;
    private final ParkRepository parkRepository;

    private final MemberPreferredFacilityRepository memberPreferredFacilityRepository;

    public List<ArtGalleryListDto> getArtGalleryList() {
        List<ArtGallery> findAll = artGalleryRepository.findAll();
        System.out.println("미술관" + findAll.get(0).getId() + findAll.get(0).getName());
        log.info("미술관" + findAll.get(0).getId() + findAll.get(0).getName() + findAll.get(0).getUrl());
        return findAll.stream().map(artGallery -> new ArtGalleryListDto(
                artGallery.getId(),
                artGallery.getName(),
                artGallery.getLikeCount(),
                artGallery.getFacilityType(), // facilityType은 고정값으로 ART_GALLERY를 사용하는 것으로 가정
                artGallery.getAddress(),
                artGallery.getPhoneNum(),
                artGallery.getUrl(),
                artGallery.getAdultFee(),
                artGallery.getChildFee()
        )).collect(Collectors.toList());
    }

    public ArtGalleryResponseDto getArtGallery(Member member, Long facilityId) {
        ArtGallery artGallery = artGalleryRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        boolean isPreferred = false;

        if (memberPreferredFacilityRepository.existsByMemberAndFacility(member, artGallery)) {
            isPreferred = true;
        }

        return ArtGalleryResponseDto.builder()
                .id(artGallery.getId())
                .name(artGallery.getName())
                .url(artGallery.getUrl())
                .phoneNum(artGallery.getPhoneNum())
                .address(artGallery.getAddress())
                .adultFee(artGallery.getAdultFee())
                .childFee(artGallery.getChildFee())
                .likeCount(artGallery.getLikeCount())
                .isPreferred(isPreferred)
                .build();

    }

    public List<KidsCafeListDto> getKidsCafeList() {
        List<KidsCafe> findAll = kidsCafeRepository.findAll();
        return findAll.stream().map(kidsCafe -> new KidsCafeListDto(
                kidsCafe.getId(),
                kidsCafe.getName(),
                kidsCafe.getFacilityType(),
                kidsCafe.getRegionGu(),
                kidsCafe.getRegionDong(),
                kidsCafe.getLatitude(),
                kidsCafe.getLongitude(),
                kidsCafe.getAddress(),
                kidsCafe.getPhoneNum(),
                kidsCafe.getClosedDays(),
                kidsCafe.getOperatingDays(),
                kidsCafe.getUsageCapacity(),
                kidsCafe.getAvailableAge(),
                kidsCafe.getLikeCount()
        )).collect(Collectors.toList());
    }

    public KidsCafeResponseDto getKidsCafe(Member member, Long facilityId) {
        KidsCafe kidsCafe = kidsCafeRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        boolean isPreferred = false;

        if (memberPreferredFacilityRepository.existsByMemberAndFacility(member, kidsCafe)) {
            isPreferred = true;
        }

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
                .likeCount(kidsCafe.getLikeCount())
                .isPreferred(isPreferred)
                .build();
    }

    public List<LibraryListDto> getLibraryList() {
        List<Library> findAll = libraryRepository.findAll();
        return findAll.stream().map(library -> new LibraryListDto(
                library.getId(),
                library.getName(),
                library.getFacilityType(), // facilityType은 고정값으로 LIBRARY를 사용하는 것으로 가정
                library.getRegionGu(),
                library.getStreet(),
                library.getPhoneNum(),
                library.getHomepageUrl(),
                library.getPostNum(),
                library.getLatitude(),
                library.getLongitude(),
                library.getOperatingTime(),
                library.getLikeCount()
        )).collect(Collectors.toList());
    }

    public LibraryResponseDto getLibrary(Member member, Long facilityId) {
        Library library = libraryRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        boolean isPreferred = false;

        if (memberPreferredFacilityRepository.existsByMemberAndFacility(member, library)) {
            isPreferred = true;
        }

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
                .likeCount(library.getLikeCount())
                .isPreferred(isPreferred)
                .build();
    }


    public List<OutdoorFacilityListDto> getOutdoorFacilityList() {
        List<OutdoorFacility> findAll = outdoorFacilityRepository.findAll();
        return findAll.stream().map(outdoorFacility -> new OutdoorFacilityListDto(
                outdoorFacility.getId(),
                outdoorFacility.getName(),
                "OUTDOOR", // facilityType은 고정값으로 OUTDOOR를 사용하는 것으로 가정
                outdoorFacility.getRegionGu(),
                outdoorFacility.getAgeClassification(),
                outdoorFacility.getLatitude(),
                outdoorFacility.getLongitude(),
                outdoorFacility.getAddress(),
                outdoorFacility.getDetailAddress(),
                outdoorFacility.getPostNum(),
                outdoorFacility.isFree(),
                outdoorFacility.getFee(),
                outdoorFacility.getUrlLink(),
                outdoorFacility.getLikeCount()
        )).collect(Collectors.toList());
    }

    public OutdoorFacilityResponseDto getOutdoorFacility(Member member, Long facilityId) {
        OutdoorFacility outdoorFacility = outdoorFacilityRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        boolean isPreferred = false;

        if (memberPreferredFacilityRepository.existsByMemberAndFacility(member, outdoorFacility)) {
            isPreferred = true;
        }

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
                .likeCount(outdoorFacility.getLikeCount())
                .isPreferred(isPreferred)
                .build();
    }


    public List<ParkListDto> getParkList() {
        List<Park> findAll = parkRepository.findAll();
        return findAll.stream().map(park -> new ParkListDto(
                park.getId(),
                park.getName(),
                "PARK", // facilityType은 고정값으로 PARK를 사용하는 것으로 가정
                park.getRegionSi(),
                park.getRegionGu(),
                park.getRegionDong(),
                park.getAddress(),
                park.getCallNumber(),
                park.getMainCategory(),
                park.getLikeCount()
        )).collect(Collectors.toList());
    }

    public ParkResponseDto getPark(Member member, Long facilityId) {
        Park park = parkRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        boolean isPreferred = false;

        if (memberPreferredFacilityRepository.existsByMemberAndFacility(member, park)) {
            isPreferred = true;
        }

        return ParkResponseDto.builder()
                .id(park.getId())
                .name(park.getName())
                .regionSi(park.getRegionSi())
                .regionGu(park.getRegionGu())
                .regionDong(park.getRegionDong())
                .address(park.getAddress())
                .mainCategory(park.getMainCategory())
                .callNumber(park.getCallNumber())
                .likeCount(park.getLikeCount())
                .isPreferred(isPreferred)
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
                    .likeCount(o.getLikeCount())
                    .build());
        }

        for (KidsCafe o: findKidsCafes) {
            result.add(FacilityResponseDto.builder()
                    .id(o.getId())
                    .name(o.getName())
                    .type("KIDS_CAFE")
                    .likeCount(o.getLikeCount())
                    .build());
        }

        for (Library o: findLibraries) {
            result.add(FacilityResponseDto.builder()
                    .id(o.getId())
                    .name(o.getName())
                    .type("LIBRARY")
                    .likeCount(o.getLikeCount())
                    .build());
        }

        for (OutdoorFacility o: findOutdoorFacilities) {
            result.add(FacilityResponseDto.builder()
                    .id(o.getId())
                    .name(o.getName())
                    .type("OUTDOOR")
                    .likeCount(o.getLikeCount())
                    .build());
        }

        for (Park o: findParks) {
            result.add(FacilityResponseDto.builder()
                    .id(o.getId())
                    .name(o.getName())
                    .type("PARK")
                    .likeCount(o.getLikeCount())
                    .build());
        }

        return result;
    }
}
