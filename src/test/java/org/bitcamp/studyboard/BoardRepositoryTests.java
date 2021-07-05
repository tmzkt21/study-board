package org.bitcamp.studyboard;

import lombok.extern.log4j.Log4j2;
import org.bitcamp.studyboard.board.entity.Board;
import org.bitcamp.studyboard.board.entity.Favorite;
import org.bitcamp.studyboard.board.repository.BoardRepository;
import org.bitcamp.studyboard.board.repository.FavoriteRepository;
import org.bitcamp.studyboard.board.repository.ReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
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

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 1000).forEach(i -> {
            boardRepository.save(Board.builder()
                    .title("게시글제목" + i)
                    .content("게시글내용" + i)
                    .writer("게시글작성자" + i)
                    .build());
        });
    }

    @Test
    public void testFavorite() {
        IntStream.rangeClosed(1, 500).forEach(i -> {
            long bno = (int) (Math.random() * 200) + 1;
            Board board = Board.builder().bno(bno).build();
            favoriteRepository.save(Favorite.builder()
                    .mark(true)
                    .actor("user" + i)
                    .board(board)
                    .build());
        });
    }
    @Test
    public void testGetData() {
        Pageable pageable = PageRequest.of(0,10);
        Page<Object[]> result = boardRepository.getData1(pageable);
        result.getContent().forEach(arr -> log.info(Arrays.toString(arr)));
    }
    @Test
    public void testSearch() {
        Pageable pageable = PageRequest.of(0,10);
        String type = "twc";
        String keyword = "10";
        Page<Object[]> page = boardRepository.getSearchList(type,keyword,pageable);
    }

}