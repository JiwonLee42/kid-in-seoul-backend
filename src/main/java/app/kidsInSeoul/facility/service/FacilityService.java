package app.kidsInSeoul.facility.service;

import app.kidsInSeoul.common.exception.CustomException;
import app.kidsInSeoul.common.exception.ErrorCode;
import app.kidsInSeoul.facility.repository.*;
import app.kidsInSeoul.facility.web.FacilityController;
import app.kidsInSeoul.facility.web.dto.response.*;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.member_preferred_facility.repository.MemberPreferredFacilityRepository;
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

    private final MemberPreferredFacilityRepository memberPreferredFacilityRepository;

    public List<ArtGalleryListDto> getArtGalleryList() {
        List<ArtGallery> findAll = artGalleryRepository.findAll();
        return findAll.stream().map(ArtGalleryListDto::new).collect(Collectors.toList());
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

    public List<KidsCafeResponseDto> getKidsCafeList() {
        List<KidsCafe> findAll = kidsCafeRepository.findAll();
        return findAll.stream().map(KidsCafeResponseDto::new).collect(Collectors.toList());
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

    public List<LibraryResponseDto> getLibraryList() {
        List<Library> findAll = libraryRepository.findAll();
        return findAll.stream().map(LibraryResponseDto::new).collect(Collectors.toList());
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


    public List<OutdoorFacilityResponseDto> getOutdoorFacilityList() {
        List<OutdoorFacility> findAll = outdoorFacilityRepository.findAll();
        return findAll.stream().map(OutdoorFacilityResponseDto::new).collect(Collectors.toList());
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


    public List<ParkResponseDto> getParkList() {
        List<Park> findAll = parkRepository.findAll();
        return findAll.stream().map(ParkResponseDto::new).collect(Collectors.toList());
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
