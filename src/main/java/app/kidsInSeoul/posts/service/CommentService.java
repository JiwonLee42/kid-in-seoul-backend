package app.kidsInSeoul.posts.service;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.posts.repository.Comment;
import app.kidsInSeoul.posts.repository.CommentRepository;
import app.kidsInSeoul.posts.web.dto.request.CommentSaveRequestDto;
import app.kidsInSeoul.posts.web.dto.response.CommentResponseDto;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class CommentService {

    CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto save(CommentSaveRequestDto requestDto, Member member) {
        if (StringUtils.isBlank(requestDto.getContent())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "댓글 내용은 필수입니다.");
        }
        Comment savedComment = commentRepository.save(requestDto.toEntity(member));
        return new CommentResponseDto(savedComment);
    }

    @Transactional
    public CommentResponseDto reSave(CommentSaveRequestDto requestDto,Member member) {
        Comment savedReComment = commentRepository.save(requestDto.toEntity(member));
        return new CommentResponseDto(savedReComment);
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findById(Long id) {
        Comment entity = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글이 없습니다. id=" + id));
        return new CommentResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findReCommentById(Long id) {
        Comment entity = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "대댓글이 존재하지 않습니다. id=" + id));
        return new CommentResponseDto(entity);
    }

    @Transactional
    public void deleteCommentById(@PathVariable Long id, Member currentUser){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글이 없습니다."));
        if(!comment.getMember().equals(currentUser)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"댓글 작성자가 아닙니다.");
        }
       commentRepository.deleteById(id);
    }

}
