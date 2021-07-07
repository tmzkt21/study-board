package org.bitcamp.studyboard.board.repository;

import org.bitcamp.studyboard.board.entity.Board;
import org.bitcamp.studyboard.board.repository.dynamic.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {

//    @Query("select b, count(distinct r), count(distinct f) from Board b " +
//            "left join Reply r on r.board = b " +
//            "left join Favorite f on f.board = b " +
//            "group by b order by b.bno desc ")

    //SELECT b , r ,f FROM 테이블1 LEFT JOIN Reply r ON r.board = b + JOIN Favorite f ON f.board = b +group by b order by b.bno desc;

//    Page<Object[]> getData1(Pageable pageable);

    @Modifying
    @Query("update Board set content =:content where bno =:bno")
    void changeContent(String content, Long bno);
}