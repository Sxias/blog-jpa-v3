package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.error.ex.Exception403;
import shop.mtcoding.blog._core.error.ex.Exception404;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public ReplyResponse.DTO 댓글쓰기(ReplyRequest.SaveDTO reqDTO, User sessionUser) {
        Reply replyPS = replyRepository.save(reqDTO.toEntity(sessionUser));
        return new ReplyResponse.DTO(replyPS);
    }

    // TODO : 2단계 - REST API 주소 변경 후 JSON 돌려주기
    @Transactional
    public void 댓글삭제(Integer id, Integer sessionUserId) {
        Reply replyPS = replyRepository.findById(id);

        // Exception404
        if (replyPS == null) throw new Exception404("자원을 찾을 수 없습니다");

        if (!replyPS.getUser().getId().equals(sessionUserId)) {
            throw new Exception403("권한이 없습니다");
        }

        replyRepository.deleteById(id);
    }
}
