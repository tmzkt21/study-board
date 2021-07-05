package org.bitcamp.studyboard.board.dto;

import lombok.Data;
import org.bitcamp.studyboard.board.common.dto.ListRequestDTO;

@Data
public class BoardListRequestDTO extends ListRequestDTO {

    private String type;

}
