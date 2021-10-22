package lkd.namsic.cnkb.domain.game.npc;

import lkd.namsic.cnkb.domain.game.item.Equipment;
import lkd.namsic.cnkb.domain.game.item.Item;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NpcShopSimple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false, length = 63)
    String simple;

    @ManyToOne
    @JoinColumn(name = "npc_shop_id", nullable = false)
    NpcShop npcShop;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    Item item;

    @ManyToOne
    @JoinColumn(name = "equip_id", nullable = false)
    Equipment equipment;

}
