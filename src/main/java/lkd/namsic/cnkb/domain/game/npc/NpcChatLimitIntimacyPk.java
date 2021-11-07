package lkd.namsic.cnkb.domain.game.npc;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@Builder
public class NpcChatLimitIntimacyPk implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "npc_chat_id")
    NpcChat npcChat;
    
    @ManyToOne
    @JoinColumn(name = "npc_id")
    Npc npc;
    
    @Override
    public int hashCode() {
        return npcChat.id.hashCode() ^ npc.id.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        
        if(obj instanceof NpcChatLimitIntimacyPk pk) {
            return npcChat.id.equals(pk.npcChat.id) &&
                npc.id.equals(pk.npc.id);
        } else {
            return false;
        }
    }
    
}