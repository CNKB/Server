package lkd.namsic.cnkb.domain.game.npc;

import lkd.namsic.cnkb.domain.game.chat.Chat;
import lkd.namsic.cnkb.domain.game.chat.ChatAnyResponse;
import lkd.namsic.cnkb.domain.game.chat.ChatResponse;
import lkd.namsic.cnkb.domain.game.quest.Quest;
import lkd.namsic.cnkb.domain.game.quest.QuestNeedIntimacy;
import lkd.namsic.cnkb.domain.game.quest.QuestRewardIntimacy;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Npc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false, length = 63, unique = true)
    String name;

    @Builder.Default
    @Column(nullable = false)
    Integer location = 0x01010101;

    @ManyToOne
    @JoinColumn(name = "first_chat_id", nullable = false)
    Chat firstChat;

    @Builder.Default
    @OneToMany(mappedBy = "npc", cascade = CascadeType.ALL)
    List<Quest> questList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "npc", cascade = CascadeType.ALL)
    List<ChatResponse> chatResponseList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "npc", cascade = CascadeType.ALL)
    List<ChatAnyResponse> chatAnyResponseList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "npc", cascade = CascadeType.ALL)
    List<NpcChat> npcChatList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "npc", cascade = CascadeType.ALL)
    List<NpcShop> npcShopList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.npc", cascade = CascadeType.ALL)
    List<QuestNeedIntimacy> questNeedIntimacyList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.npc", cascade = CascadeType.ALL)
    List<QuestRewardIntimacy> questRewardIntimacyList = new ArrayList<>();

}
