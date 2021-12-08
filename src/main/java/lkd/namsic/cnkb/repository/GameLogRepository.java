package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.game.GameLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameLogRepository extends JpaRepository<GameLog, Long> {
}
