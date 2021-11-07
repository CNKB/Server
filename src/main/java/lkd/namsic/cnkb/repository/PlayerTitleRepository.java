package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.game.player.PlayerTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerTitleRepository extends JpaRepository<PlayerTitle, Long> {
}
