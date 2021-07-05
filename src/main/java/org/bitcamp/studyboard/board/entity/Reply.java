package org.bitcamp.studyboard.board.entity;


import lombok.*;
import org.bitcamp.studyboard.board.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reply")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    private String replyText;

    @ManyToOne
    Board board;

    public void change(String replyText) {
        this.replyText = replyText;
    }
}
