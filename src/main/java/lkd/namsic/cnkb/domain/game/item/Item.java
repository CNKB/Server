package lkd.namsic.cnkb.domain.game.item;

import lkd.namsic.cnkb.domain.game.living.LivingItem;
import lkd.namsic.cnkb.domain.game.map.GameMapFieldItem;
import lkd.namsic.cnkb.domain.game.npc.NpcShop;
import lkd.namsic.cnkb.domain.game.npc.NpcShopSimple;
import lkd.namsic.cnkb.domain.game.quest.QuestNeedItem;
import lkd.namsic.cnkb.domain.game.quest.QuestRewardItem;
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
public class Item {

    @Id
    @Column
    Long id;

    @Column(nullable = false, length = 63)
    String des;

    @Column(length = 63)
    String gainDes;

    @Column(length = 63)
    String useDes;

    @Column(length = 63)
    String eatDes;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    Plant plant;

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<ItemBuffStat> itemBuffStatList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "rewardItem", cascade = CascadeType.ALL)
    List<Plant> plantRewardList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<LivingItem> livingItemList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<ItemRecipe> itemRecipeList1 = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "recipeItem", cascade = CascadeType.ALL)
    List<ItemRecipe> itemRecipeList2 = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "recipeItem", cascade = CascadeType.ALL)
    List<EquipRecipe> equipRecipeList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.item", cascade = CascadeType.ALL)
    List<GameMapFieldItem> gameMapFieldItemList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<NpcShop> npcShopList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<NpcShopSimple> npcShopSimpleList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.item", cascade = CascadeType.ALL)
    List<QuestNeedItem> questNeedItemList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.item", cascade = CascadeType.ALL)
    List<QuestRewardItem> questRewardItemList = new ArrayList<>();

}