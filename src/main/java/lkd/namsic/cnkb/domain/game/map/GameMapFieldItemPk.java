package lkd.namsic.cnkb.domain.game.map;

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
public class GameMapFieldItemPk implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "field_location")
    GameMapField gameMapField;
    
    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;
    
    @Override
    public int hashCode() {
        return gameMapField.gameMap.location.hashCode() ^ item.getId().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        
        if(obj instanceof GameMapFieldItemPk pk) {
            return gameMapField.gameMap.location.equals(pk.gameMapField.gameMap.location) &&
                item.getId().equals(pk.item.getId());
        } else {
            return false;
        }
    }
    
}