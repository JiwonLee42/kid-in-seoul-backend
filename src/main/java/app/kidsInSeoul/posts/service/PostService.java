package app.kidsInSeoul.posts.service;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.web.dto.response.PostResponseDto;
import app.kidsInSeoul.posts.web.dto.request.PostUpdateRequestDto;
import app.kidsInSeoul.posts.web.dto.request.PostsSaveRequestDto;
import app.kidsInSeoul.posts.repository.Posts;
import app.kidsInSeoul.posts.repository.PostsRepository;
import app.kidsInSeoul.region.repository.Region;
import app.kidsInSeoul.region.repository.RegionRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostsRepository postsRepository;

    private final RegionRepository regionRepository;


    @Transactional
    public PostResponseDto save(PostsSaveRequestDto requestDto, Member member) {
        // 게시글 내용이 비어있는 경우 예외 처리
        if (StringUtils.isBlank(requestDto.getContent())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글 내용은 필수입니다.");
        }
        Posts savedPost = postsRepository.save(requestDto.toEntity(member,member.getRegion()));
        return new PostResponseDto(savedPost);
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. id=" + id));
        return new PostResponseDto(entity);
    }

    @Transactional
    public PostResponseDto update(Long id, PostUpdateRequestDto requestDto, Member currentUser){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. id = " + id));
        if(!posts.getMember().equals(currentUser)) {
            throw  new ResponseStatusException(HttpStatus.FORBIDDEN,"게시글 작성자가 아닙니다.");
        }
        posts.update(requestDto.getTitle(),requestDto.getContent());
        return new PostResponseDto(posts);
    }

    @Transactional
    public void deleteById(Long id,Member currentUser) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. id=" + id));
        if(!post.getMember().getId().equals(currentUser.getId())) {
            throw  new ResponseStatusException(HttpStatus.FORBIDDEN,"게시글 작성자가 아닙니다.");
        }
        postsRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<PostResponseDto> findByRegion(Long regionId, Pageable pageable){
        Page<Posts> posts = postsRepository.findByRegionId(regionId,pageable);
        return posts.getContent().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findByRegionByLiked(Long regionId, Pageable pageable){
        Page<Posts> posts = postsRepository.findByRegionIdMostLiked(regionId,pageable);
        return posts.getContent().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto likePosts(Long postId){
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. id=" + postId));
        postsRepository.incrementLikes(postId);
        return new PostResponseDto(posts);
    }

    @Transactional
    public PostResponseDto likePostsCancel(Long postId){
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. id=" + postId));
        postsRepository.decreaseLikes(postId);
        return new PostResponseDto(posts);
    }



}
