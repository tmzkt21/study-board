package org.bitcamp.studyboard.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bitcamp.studyboard.board.common.dto.ListResponseDTO;
import org.bitcamp.studyboard.board.dto.BoardDTO;
import org.bitcamp.studyboard.board.dto.BoardListRequestDTO;
import org.bitcamp.studyboard.board.dto.ListBoardDTO;
import org.bitcamp.studyboard.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<ListBoardDTO>> list(BoardListRequestDTO requestDTO) {

        return ResponseEntity.ok(boardService.getList(requestDTO));
    }

}
