package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.game.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findAllByUserId(long userId);

}
