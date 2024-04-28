package app.kidsInSeoul.posts.web;
import app.kidsInSeoul.jwt.service.dto.CustomUserDetails;
import app.kidsInSeoul.posts.repository.Posts;
import app.kidsInSeoul.posts.service.CommentService;
import app.kidsInSeoul.posts.service.PostService;
import app.kidsInSeoul.posts.web.dto.request.CommentSaveRequestDto;
import app.kidsInSeoul.posts.web.dto.request.PostUpdateRequestDto;
import app.kidsInSeoul.posts.web.dto.request.PostsSaveRequestDto;
import app.kidsInSeoul.posts.web.dto.request.ReCommentSaveRequestDto;
import app.kidsInSeoul.posts.web.dto.response.CommentResponseDto;
import app.kidsInSeoul.posts.web.dto.response.PostResponseDto;
import app.kidsInSeoul.region.repository.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    @Autowired
    private final PostService postsService;

    @Autowired
    private final CommentService commentService;


    @PostMapping("/posts/write")
    public ResponseEntity<PostResponseDto> save(@RequestBody PostsSaveRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        PostResponseDto responseDto = postsService.save(requestDto, userDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @PutMapping("/posts/edit/{postId}")
    public ResponseEntity<PostResponseDto> editById (@PathVariable Long postId, @RequestBody PostUpdateRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        PostResponseDto responseDto = postsService.update(postId,requestDto,userDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/posts/delete/{postId}")
    public ResponseEntity<String> deleteById(@PathVariable Long postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        postsService.deleteById(postId,userDetails.getMember());
        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }

    @GetMapping("/posts/region/{regionId}")
    public ResponseEntity<List<PostResponseDto>> findByRegion (@PathVariable Long regionId, @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<PostResponseDto> posts = postsService.findByRegion(regionId,pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/posts/region/like/{regionId}")
    public ResponseEntity<List<PostResponseDto>> findByRegionByLiked (@PathVariable Long regionId, @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<PostResponseDto> posts = postsService.findByRegion(regionId,pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }



    @PostMapping("/posts/comment/write")
    public ResponseEntity<CommentResponseDto> saveComment(@RequestBody CommentSaveRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        CommentResponseDto responseDto = commentService.save(requestDto, userDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/posts/recomment/write")
    public ResponseEntity<CommentResponseDto> saveReComment(@RequestBody ReCommentSaveRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        CommentResponseDto responseDto = commentService.reSave(requestDto, userDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/posts/comments/{postId}")
    public ResponseEntity<List<CommentResponseDto>> findCommentById(@PathVariable Long postId){
        List<CommentResponseDto> responseDto = commentService.findById(postId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/posts/recomment/{commentId}")
    public ResponseEntity<List<CommentResponseDto>> findRecommentById(@PathVariable Long commentId){
        List<CommentResponseDto> responseDto = commentService.findReCommentById(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/posts/comment/delete/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Long commentId,@AuthenticationPrincipal CustomUserDetails userDetails){
        commentService.deleteCommentById(commentId,userDetails.getMember());
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }

    @PatchMapping("/posts/like/{postId}")
    public ResponseEntity<String> likePosts(@PathVariable Long postId){
        postsService.likePosts(postId);
        return ResponseEntity.ok("좋아요 반영되었습니다.");
    }


}
