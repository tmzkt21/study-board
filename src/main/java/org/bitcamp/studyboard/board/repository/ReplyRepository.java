package org.bitcamp.studyboard.board.repository;

import org.bitcamp.studyboard.board.entity.Board;
import org.bitcamp.studyboard.board.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Page<Reply> getByBoard(Board board , Pageable pageable);
}
