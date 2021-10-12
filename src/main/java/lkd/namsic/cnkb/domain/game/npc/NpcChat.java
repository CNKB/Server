package lkd.namsic.cnkb.domain.game.npc;

import lkd.namsic.cnkb.domain.game.chat.Chat;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NpcChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer minLv = 1;

    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer maxLv = 999;

    @Builder.Default
    @Column(nullable = false)
    Time beginTime1 = Time.valueOf("00:00:00");

    @Builder.Default
    @Column(nullable = false)
    Time endTime1 = Time.valueOf("23:59:59");

    @Column
    Time beginTime2;

    @Column
    Time endTime2;

    @ManyToOne
    @JoinColumn(name = "npc_id", nullable = false)
    Npc npc;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    Chat chat;

    @Builder.Default
    @OneToMany(mappedBy = "pk.npcChat", cascade = CascadeType.ALL)
    List<NpcChatLimitCurrentQuest> npcChatLimitCurrentQuestList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.npcChat", cascade = CascadeType.ALL)
    List<NpcChatLimitIntimacy> npcChatLimitIntimacyList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.npcChat", cascade = CascadeType.ALL)
    List<NpcChatLimitQuest> npcChatLimitQuestList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.npcChat", cascade = CascadeType.ALL)
    List<NpcChatLimitStat> npcChatLimitStatList = new ArrayList<>();

}
