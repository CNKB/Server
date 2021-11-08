package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.game.living.LivingVariableUnique;
import lkd.namsic.cnkb.domain.game.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivingVariableUniqueRepository extends JpaRepository<LivingVariableUnique, Long> {

    Optional<LivingVariableUnique> findByPlayer(Player player);

}