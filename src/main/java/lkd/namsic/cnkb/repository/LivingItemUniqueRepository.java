package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.game.item.Item;
import lkd.namsic.cnkb.domain.game.living.LivingItemUnique;
import lkd.namsic.cnkb.domain.game.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivingItemUniqueRepository extends JpaRepository<LivingItemUnique, Long> {

    Optional<LivingItemUnique> findByPlayerAndItem(Player player, Item item);

}