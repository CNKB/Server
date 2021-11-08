package lkd.namsic.cnkb.domain.game.item;

import lkd.namsic.cnkb.domain.game.living.LivingEquip;
import lkd.namsic.cnkb.domain.game.npc.NpcShop;
import lkd.namsic.cnkb.domain.game.npc.NpcShopSimple;
import lkd.namsic.cnkb.domain.game.quest.QuestNeedEquip;
import lkd.namsic.cnkb.domain.game.quest.QuestRewardEquip;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false, length = 63, unique = true)
    String name;

    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer type;

    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer handleLv;

    @Column(nullable = false, length = 63)
    String des;

    @Column(length = 63)
    String gainDes;

    @Column(length = 63)
    String recipeGainDes;

    @Column(length = 63)
    String passiveDes;

    @Column(length = 63)
    String activeDes;

    @Builder.Default
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    List<LivingEquip> livingEquipList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    List<EquipEvent> equipEventList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "recipeEquip", cascade = CascadeType.ALL)
    List<ItemRecipe> itemRecipeList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    List<EquipRecipe> equipRecipeList1 = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "recipeEquip", cascade = CascadeType.ALL)
    List<EquipRecipe> equipRecipeList2 = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    List<NpcShop> npcShopList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    List<NpcShopSimple> npcShopSimpleList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    List<QuestNeedEquip> questNeedEquipList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.equipment", cascade = CascadeType.ALL)
    List<QuestRewardEquip> questRewardEquipList = new ArrayList<>();

}