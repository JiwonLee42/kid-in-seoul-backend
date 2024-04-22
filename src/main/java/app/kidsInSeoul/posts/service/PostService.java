package app.kidsInSeoul.posts.service;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.web.dto.response.PostResponseDto;
import app.kidsInSeoul.posts.web.dto.request.PostUpdateRequestDto;
import app.kidsInSeoul.posts.web.dto.request.PostsSaveRequestDto;
import app.kidsInSeoul.posts.repository.Posts;
import app.kidsInSeoul.posts.repository.PostsRepository;
import app.kidsInSeoul.posts.repository.QPosts;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostsRepository postsRepository;

    // 권한 예외처리 추가하기

    @Autowired
    private JPAQueryFactory queryFactory;
    @Transactional
    public PostResponseDto save(PostsSaveRequestDto requestDto, Member member) {
        // 게시글 내용이 비어있는 경우 예외 처리
        if (StringUtils.isBlank(requestDto.getContent())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글 내용은 필수입니다.");
        }
        Posts savedPost = postsRepository.save(requestDto.toEntity(member));
        return new PostResponseDto(savedPost);
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. id=" + id));
        return new PostResponseDto(entity);
    }

    @Transactional
    public PostUpdateRequestDto update(Long id, PostUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return requestDto;
    }

    @Transactional
    public void deleteById(Long id,Member currentUser) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. id=" + id));
        if(!post.getMember().equals(currentUser)) {
            throw  new ResponseStatusException(HttpStatus.FORBIDDEN,"게시글 작성자가 아닙니다.");
        }

        postsRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<Posts> findByRegion(Long regionId){
        QPosts qposts = QPosts.posts;
        return queryFactory
                .selectFrom(qposts)
                .where(qposts.region.region_id.eq(regionId))
                .fetch();
    }



}
