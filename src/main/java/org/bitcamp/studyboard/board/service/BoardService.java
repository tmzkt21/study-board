package org.bitcamp.studyboard.board.service;

import org.bitcamp.studyboard.board.common.dto.ListResponseDTO;
import org.bitcamp.studyboard.board.dto.BoardDTO;
import org.bitcamp.studyboard.board.dto.BoardListRequestDTO;
import org.bitcamp.studyboard.board.dto.ListBoardDTO;
import org.bitcamp.studyboard.board.entity.Board;

public interface BoardService {
    ListResponseDTO<ListBoardDTO> getList(BoardListRequestDTO requestDTO);

    default ListBoardDTO arrToDTO(Object[] arr) {
        Board board = (Board)arr[0];
        long replyCount = (Long)arr[1];
        long favoriteCount =(Long)arr[2];

        return ListBoardDTO.builder()
                .boardDTO(entityToDTO(board))
                .likeCount(favoriteCount)
                .replyCount(replyCount)
                .build();
    }
    default BoardDTO entityToDTO(Board board) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .regData(board.getRegDate())
                .modData(board.getModDate())
                .build();
    }
}
