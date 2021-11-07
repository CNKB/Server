package lkd.namsic.cnkb.domain.game.quest;

import lkd.namsic.cnkb.domain.game.npc.Npc;
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
public class QuestNeedIntimacyPk implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "quest_id")
    Quest quest;
    
    @ManyToOne
    @JoinColumn(name = "npc_id")
    Npc npc;
    
    @Override
    public int hashCode() {
        return quest.id.hashCode() ^ npc.getId().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        
        if(obj instanceof QuestNeedIntimacyPk pk) {
            return quest.id.equals(pk.quest.id) &&
                npc.getId().equals(pk.npc.getId());
        } else {
            return false;
        }
    }
    
}