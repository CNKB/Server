package lkd.namsic.cnkb.domain.game.npc;

import lkd.namsic.cnkb.domain.game.item.Equipment;
import lkd.namsic.cnkb.domain.game.item.Item;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NpcShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(columnDefinition = "BIGINT UNSIGNED NOT NULL")
    BigInteger price;

    @Builder.Default
    @Column(nullable = false)
    Boolean isSelling = true;

    @Builder.Default
    @Column(nullable = false)
    Boolean canDiscount = true;

    @ManyToOne
    @JoinColumn(name = "npc_id", nullable = false)
    Npc npc;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    Item item;

    @ManyToOne
    @JoinColumn(name = "equip_id", nullable = false)
    Equipment equipment;

    @Builder.Default
    @OneToMany(mappedBy = "npcShop", cascade = CascadeType.ALL)
    List<NpcShopSimple> npcShopSimpleList = new ArrayList<>();

}
