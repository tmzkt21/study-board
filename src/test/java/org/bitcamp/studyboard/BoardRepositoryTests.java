package org.bitcamp.studyboard;

import lombok.extern.log4j.Log4j2;
import org.bitcamp.studyboard.board.entity.Board;
import org.bitcamp.studyboard.board.entity.Favorite;
import org.bitcamp.studyboard.board.entity.Reply;
import org.bitcamp.studyboard.board.repository.BoardRepository;
import org.bitcamp.studyboard.board.repository.FavoriteRepository;
import org.bitcamp.studyboard.board.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class BoardRepositoryTests {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    FavoriteRepository favoriteRepository;

    // create
    @Test
    public void testCreate() {
        IntStream.rangeClosed(1, 200).forEach(i -> {

            Board board = Board.builder()
                    .title("게시판제목" + i)
                    .content("게시판내용" + i)
                    .writer("작성자" + i)
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    void testReplyCreat() {
        IntStream.rangeClosed(0, 1000).forEach(i -> {
                    long bno = (int) (Math.random() * 200) + 1;
                    Board board = Board.builder().bno(bno).build();
                    Reply reply = Reply.builder()
                            .replyText("댓글" + i)
                            .board(board)
                            .build();
                    replyRepository.save(reply);

                }

        );


    }

    //read

    @Test
    public void createRead() {
        Optional<Board> board = boardRepository.findById(23L);
        board.ifPresent(todo -> {
            log.info(todo + "투두");
        });
    }


    // 26
    @Test
    public void testDelete() {

        Optional<Board> board = boardRepository.findById(23L);
        Optional<Reply> reply = replyRepository.findById(23L);
        board.ifPresent(todo -> {
            log.info(todo + "투두");
        });
        reply.ifPresent(todo -> {
            replyRepository.delete(todo);
        });

    }

    @Test
    public void testUpdate() {
        Optional<Board> board = boardRepository.findById(30L);
        board.ifPresent(todo -> {
            todo.change("바뀐제목");
            boardRepository.save(todo);
        });
    }

    @Test
    @Transactional
    @Commit
    public void testUpdate2 () {
        boardRepository.changeContent("바뀌는내용",26L);
    }



}