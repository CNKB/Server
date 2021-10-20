package lkd.namsic.cnkb.domain.game.quest;

import lkd.namsic.cnkb.domain.game.chat.Chat;
import lkd.namsic.cnkb.domain.game.map.GameMapLimitQuest;
import lkd.namsic.cnkb.domain.game.npc.Npc;
import lkd.namsic.cnkb.domain.game.npc.NpcChatLimitCurrentQuest;
import lkd.namsic.cnkb.domain.game.npc.NpcChatLimitIntimacy;
import lkd.namsic.cnkb.domain.game.npc.NpcChatLimitQuest;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false, length = 63, unique = true)
    String name;

    @Builder.Default
    @Column
    Integer minLv = 1;

    @Builder.Default
    @Column(columnDefinition = "BIGINT UNSIGNED NOT NULL")
    BigInteger needMoney = new BigInteger("0");

    @Builder.Default
    @Column(columnDefinition = "BIGINT UNSIGNED NOT NULL")
    BigInteger rewardMoney = new BigInteger("0");

    @Builder.Default
    @Column(nullable = false)
    Long rewardExp = 0L;

    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer rewardAdv = 0;

    @ManyToOne
    @JoinColumn(name = "clear_npc_id", nullable = false)
    Npc npc;

    @Builder.Default
    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    List<Chat> chatList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<GameMapLimitQuest> gameMapLimitQuestList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<NpcChatLimitCurrentQuest> npcChatLimitCurrentQuestList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<NpcChatLimitIntimacy> npcChatLimitIntimacyList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<NpcChatLimitQuest> npcChatLimitQuestList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    List<QuestNeedEquip> questNeedEquipList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<QuestNeedItem> questNeedItemList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<QuestNeedIntimacy> questNeedIntimacyList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<QuestRewardEquip> questRewardEquipList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<QuestRewardEquipRecipe> questRewardEquipRecipeList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<QuestRewardIntimacy> questRewardIntimacyList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<QuestRewardItem> questRewardItemList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<QuestRewardItemRecipe> questRewardItemRecipeList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.quest", cascade = CascadeType.ALL)
    List<QuestRewardStat> questRewardStatList = new ArrayList<>();

}
