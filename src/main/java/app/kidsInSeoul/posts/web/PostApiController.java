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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    @Autowired
    private PostService postsService;

    @Autowired
    private CommentService commentService;



    @PostMapping("/posts/write")
    public ResponseEntity<PostResponseDto> save(@RequestBody PostsSaveRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        PostResponseDto responseDto = postsService.save(requestDto, userDetails.getMember(), requestDto.getMember().getRegion());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    // 나머지 api도 수정하기 !

    // post 현재
    @GetMapping("/posts/{post_id}")
    public PostResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @PutMapping("/posts/edit/{post_id}")
    public ResponseEntity<PostResponseDto> editById (@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        PostResponseDto responseDto = postsService.update(id,requestDto,userDetails.getMember());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/posts/delete/{post_id}")
    public ResponseEntity<String> deleteByid(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        postsService.deleteById(id,userDetails.getMember());
        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }

    @GetMapping("/posts/region/{region_id}")
    public ResponseEntity<List<PostResponseDto>> findByRegion (@PathVariable Long id) {
        List<PostResponseDto> posts = postsService.findByRegion(id);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @PostMapping("/posts/comment/write")
    public ResponseEntity<CommentResponseDto> saveComment(@RequestBody CommentSaveRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        CommentResponseDto responseDto = commentService.save(requestDto, userDetails.getMember());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/posts/recomment/write")
    public ResponseEntity<CommentResponseDto> saveReComment(@RequestBody ReCommentSaveRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails userDetails){
        CommentResponseDto responseDto = commentService.reSave(requestDto, userDetails.getMember());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/posts/comment/{post_id}")
    public ResponseEntity<CommentResponseDto> findCommentById(@PathVariable Long id){
        CommentResponseDto responseDto = commentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/posts/comment/{comment_id")
    public ResponseEntity<CommentResponseDto> findRecommentById(@PathVariable Long id){
        CommentResponseDto responseDto = commentService.findReCommentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/posts/comment/delete/{comment_id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Long id,@AuthenticationPrincipal CustomUserDetails userDetails){
        commentService.deleteCommentById(id,userDetails.getMember());
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }

}
