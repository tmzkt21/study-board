package org.bitcamp.studyboard.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bitcamp.studyboard.board.common.dto.ListResponseDTO;
import org.bitcamp.studyboard.board.dto.BoardDTO;
import org.bitcamp.studyboard.board.dto.BoardListRequestDTO;
import org.bitcamp.studyboard.board.dto.ListBoardDTO;
import org.bitcamp.studyboard.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    //create
    @PostMapping("/create")
    public ResponseEntity<BoardDTO> register(@RequestBody BoardDTO boardDTO) {

        boardService.insert(boardDTO);

        log.info(boardDTO + "가입완료");
        return ResponseEntity.ok().body(boardDTO);
    }

    // read
    @GetMapping("/{bno}")
    public ResponseEntity<BoardDTO> read(@PathVariable Long bno) {

        BoardDTO boardDTO = boardService.read(bno);

        return ResponseEntity.ok().body(boardDTO);
    }

    //update
    @PutMapping("/{bno}")
    public ResponseEntity<BoardDTO> update(@PathVariable Long bno, @RequestBody BoardDTO boardDTO) {

        boardDTO.setBno(bno);

        BoardDTO resultDTO = boardService.update(boardDTO);


        return ResponseEntity.ok(boardService.update(boardDTO));
    }

    //delete
    @DeleteMapping("/{tno}")
    public ResponseEntity<Long> remove(@PathVariable Long bno) {

        Long deletedBno = boardService.remove(bno);

        return ResponseEntity.ok().body(deletedBno);
    }


}
