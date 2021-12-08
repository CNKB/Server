package lkd.namsic.cnkb.repository;

import lkd.namsic.cnkb.domain.game.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
