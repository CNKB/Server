package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.game.entity.LivingVariable;
import lkd.namsic.cnkb.domain.game.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivingVariableRepository extends JpaRepository<LivingVariable, Long> {
    
    Optional<LivingVariable> findByPlayerAndVariable(Player player, int variable);
    
}