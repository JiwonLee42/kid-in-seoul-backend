package app.kidsInSeoul.posts.web;
import app.kidsInSeoul.posts.repository.Posts;
import app.kidsInSeoul.posts.service.PostService;
import app.kidsInSeoul.posts.web.request.PostUpdateRequestDto;
import app.kidsInSeoul.posts.web.request.PostsSaveRequestDto;
import app.kidsInSeoul.posts.web.response.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    @Autowired
    private PostService postsService;


    @PostMapping("/posts/write")
    public Long save(@RequestBody PostsSaveRequestDto
                      requestDto){
        return postsService.save(requestDto);
    }


    @GetMapping("/posts/{post_id}")
    public PostResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @PutMapping("/posts/edit/{post_id}")
    public PostUpdateRequestDto editById (@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto){
        return postsService.update(id,requestDto);
    }

    @DeleteMapping("/posts/delete/{post_id}")
    public void deleteByid(@PathVariable Long id) { postsService.deleteById(id);}

    @GetMapping("/posts/region/{region_id}")
    public List<Posts> findByRegion (@PathVariable Long id) { return postsService.findByRegion(id);}

    /*
     return ResponseEntity.status(HttpStatus.OK).body(memberLoginResponseDto);

     */
}
