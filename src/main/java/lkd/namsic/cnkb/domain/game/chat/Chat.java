package lkd.namsic.cnkb.domain.game.chat;

import lkd.namsic.cnkb.domain.game.entity.Entity;
import lkd.namsic.cnkb.domain.game.npc.Npc;
import lkd.namsic.cnkb.domain.game.npc.NpcChat;
import lkd.namsic.cnkb.domain.game.quest.Quest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Chat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    
    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer startDelayTime = 0;
    
    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer chatDelayTime = 0;
    
    @Column
    Integer tpLocation;
    
    @Builder.Default
    @Column(nullable = false)
    Boolean isOption = true;
    
    @ManyToOne
    @JoinColumn(name = "quest_id")
    Quest quest;
    
    @Builder.Default
    @OneToMany(mappedBy = "spawnChat", cascade = CascadeType.ALL)
    List<Entity> entityList1 = new ArrayList<>();
    
    @Builder.Default
    @OneToMany(mappedBy = "fightChat", cascade = CascadeType.ALL)
    List<Entity> entityList2 = new ArrayList<>();
    
    @Builder.Default
    @OneToMany(mappedBy = "firstChat", cascade = CascadeType.ALL)
    List<Npc> npcList = new ArrayList<>();
    
    @Builder.Default
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    List<ChatResponse> chatResponseList1 = new ArrayList<>();
    
    @Builder.Default
    @OneToMany(mappedBy = "targetChat", cascade = CascadeType.ALL)
    List<ChatResponse> chatResponseList2 = new ArrayList<>();
    
    @Builder.Default
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    List<ChatAnyResponse> chatAnyResponseList1 = new ArrayList<>();
    
    @Builder.Default
    @OneToMany(mappedBy = "targetChat", cascade = CascadeType.ALL)
    List<ChatAnyResponse> chatAnyResponseList2 = new ArrayList<>();
    
    @Builder.Default
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    List<NpcChat> npcChatList = new ArrayList<>();
    
}