package org.bitcamp.studyboard.board.entity;

import lombok.*;
import org.bitcamp.studyboard.board.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favorite")
@Builder
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    private boolean mark;

    private String actor;

    @ManyToOne
    private Board board;
}
