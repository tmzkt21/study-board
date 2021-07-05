package org.bitcamp.studyboard;


import lombok.extern.log4j.Log4j2;
import org.bitcamp.studyboard.board.entity.Board;
import org.bitcamp.studyboard.board.entity.Reply;
import org.bitcamp.studyboard.board.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@ActiveProfiles("dev")
@Log4j2
public class ReplyRepositoryTests {
    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void testInsult() {
        IntStream.rangeClosed(1, 1000).forEach(i -> {
            long bno = (int) (Math.random() * 200) + 1;
            Board board = Board.builder().bno(bno).build();

            replyRepository.save(Reply.builder()
                    .replyText("댓글내용" + i)
                    .board(board)
                    .build());
        });
    }
    @Test
    public void testRead() {

        //1번 댓글
        Optional<Reply> result = replyRepository.findById(1L);

        log.info(result);

        result.ifPresent(reply -> log.info(reply));

    }

    @Test
    public void testPaging() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());

        Page<Reply> result = replyRepository.findAll(pageable);

        log.info(result);

        result.getContent().forEach(reply -> {
            log.info(reply);
            log.info("------------------------");
        });
    }

    @Test
    public void testByBoard() {
        Board board = Board.builder().bno(20L).build();
        Pageable pageable = PageRequest.of(0,10);
        replyRepository.getByBoard(board,pageable);
        log.info(replyRepository.getByBoard(board,pageable) + "결과");
    }

}
