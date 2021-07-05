package org.bitcamp.studyboard.board.repository;

import org.bitcamp.studyboard.board.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
