package lkd.namsic.cnkb.domain.game.quest;

import lkd.namsic.cnkb.domain.game.item.Item;
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
public class QuestNeedItemPk implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "quest_id")
    Quest quest;
    
    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;
    
    @Override
    public int hashCode() {
        return quest.id.hashCode() ^ item.getId().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        
        if(obj instanceof QuestNeedItemPk pk) {
            return quest.id.equals(pk.quest.id) &&
                item.getId().equals(pk.item.getId());
        } else {
            return false;
        }
    }
    
}