package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.game.map.GameMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameMapRepository extends JpaRepository<GameMap, Integer> {}
