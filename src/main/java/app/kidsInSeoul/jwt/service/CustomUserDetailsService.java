package app.kidsInSeoul.jwt.service;

import app.kidsInSeoul.common.exception.CustomException;
import app.kidsInSeoul.common.exception.ErrorCode;
import app.kidsInSeoul.jwt.service.dto.CustomUserDetails;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("User ID = {}", userId);
        Optional<Member> member = memberRepository.findByUserId(userId);
        if (member.isEmpty()) {
            member = memberRepository.findByEmail(userId);
        }
        if (member.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_MEMBER);
        }

        return new CustomUserDetails(member.get());
    }


}
