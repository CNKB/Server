package lkd.namsic.cnkb.domain.game.map;

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
public class GameMapLimitQuestPk implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "location")
    GameMap gameMap;
    
    @ManyToOne
    @JoinColumn(name = "quest_id")
    Quest quest;
    
    @Override
    public int hashCode() {
        return gameMap.location.hashCode() ^ quest.getId().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        
        if(obj instanceof GameMapLimitQuestPk pk) {
            return gameMap.location.equals(pk.gameMap.location) &&
                quest.getId().equals(pk.quest.getId());
        } else {
            return false;
        }
    }
    
}