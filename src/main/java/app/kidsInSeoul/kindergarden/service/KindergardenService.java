package app.kidsInSeoul.kindergarden.service;


import app.kidsInSeoul.kindergarden.repository.Kindergarden;
import app.kidsInSeoul.kindergarden.repository.KindergardenRepository;
import app.kidsInSeoul.kindergarden.web.dto.request.KindergardenSaveRequestDto;
import app.kidsInSeoul.kindergarden.web.dto.response.KindergardenResponseDto;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.member.repository.MemberRepository;
import app.kidsInSeoul.posts.repository.Posts;
import app.kidsInSeoul.posts.web.dto.response.PostResponseDto;
import app.kidsInSeoul.region.repository.Region;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KindergardenService {

    private final KindergardenRepository kindergardenRepository;

    private final MemberRepository memberRepository;


    @Transactional(readOnly = true)
    public List<KindergardenResponseDto> findByRegionId(Long regionId) {
        List<Kindergarden> kindergardens = kindergardenRepository.findByRegion(regionId);
        return kindergardens.stream().map(b -> new KindergardenResponseDto(b)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<KindergardenResponseDto> findNightOp(Long regionId) {
        List<Kindergarden> kindergardens = kindergardenRepository.findNightOp(regionId);
        return kindergardens.stream().map(b -> new KindergardenResponseDto(b)).collect(Collectors.toList());
    }

}
