package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.game.living.LivingItem;
import lkd.namsic.cnkb.domain.game.living.LivingItemUnique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivingItemRepository extends JpaRepository<LivingItem, Long> {

    Optional<LivingItem> findByLiving(LivingItemUnique living);

}