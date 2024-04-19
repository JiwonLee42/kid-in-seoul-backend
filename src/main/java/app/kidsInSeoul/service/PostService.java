package app.kidsInSeoul.service;

import app.kidsInSeoul.posts.web.PostResponseDto;
import app.kidsInSeoul.posts.web.PostUpdateRequestDto;
import app.kidsInSeoul.posts.web.PostsSaveRequestDto;
import app.kidsInSeoul.posts.repository.Posts;
import app.kidsInSeoul.posts.repository.PostsRepository;
import app.kidsInSeoul.posts.repository.QPosts;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostsRepository postsRepository;

    @Autowired
    private JPAQueryFactory queryFactory;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity())
                .getPost_id();
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostResponseDto(entity);
    }

    @Transactional
    public PostUpdateRequestDto update(Long id, PostUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return requestDto;
    }

    @Transactional
    public void deleteById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
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
