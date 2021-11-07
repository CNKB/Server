package lkd.namsic.cnkb.domain.game.npc;

import lkd.namsic.cnkb.domain.game.quest.Quest;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NpcChatLimitCurrentQuestPk implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "npc_chat_id")
    NpcChat npcChat;
    
    @ManyToOne
    @JoinColumn(name = "quest_id")
    Quest quest;
    
    @Override
    public int hashCode() {
        return npcChat.id.hashCode() ^ quest.getId().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        
        if(obj instanceof NpcChatLimitCurrentQuestPk pk) {
            return npcChat.id.equals(pk.npcChat.id) &&
                quest.getId().equals(pk.quest.getId());
        } else {
            return false;
        }
    }
    
}