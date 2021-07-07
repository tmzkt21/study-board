package org.bitcamp.studyboard.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bitcamp.studyboard.board.common.dto.ListResponseDTO;
import org.bitcamp.studyboard.board.common.dto.PageMaker;
import org.bitcamp.studyboard.board.dto.BoardDTO;
import org.bitcamp.studyboard.board.dto.BoardListRequestDTO;
import org.bitcamp.studyboard.board.dto.ListBoardDTO;
import org.bitcamp.studyboard.board.entity.Board;
import org.bitcamp.studyboard.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public ListResponseDTO<ListBoardDTO> getList(BoardListRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable();
        Page<Object[]> result = boardRepository.getSearchList(requestDTO.getType(), requestDTO.getKeyword(), pageable);

        List<ListBoardDTO> boardDTOList = result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(requestDTO.getPage(), requestDTO.getSize(), (int) result.getTotalElements());

        return ListResponseDTO.<ListBoardDTO>builder()
                .dtoList(boardDTOList)
                .pageMaker(pageMaker)
                .listRequestDTO(null)
                .build();
    }

    @Override
    public BoardDTO insert(BoardDTO boardDTO) {

        Board entity = dtoToEntity(boardDTO);
        boardRepository.save(entity);

        return null;
    }

    @Override
    public BoardDTO read(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);


        if (result.isPresent()) {
            Board todo = result.get();
            return entityToDTO(todo);
        }


        return null;
    }

    @Override
    public BoardDTO update(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        if (result.isPresent()) {

            Board entity = result.get();
            entity.changeTitle(boardDTO.getTitle());
            entity.changeContent(boardDTO.getContent());
            entity.changeWriter(boardDTO.getWriter());
            boardRepository.save(entity);

            return entityToDTO(entity);
        }
        return null;
    }

    @Override
    public Long remove(Long bno) {

        boardRepository.deleteById(bno);
        return bno;
    }
}
