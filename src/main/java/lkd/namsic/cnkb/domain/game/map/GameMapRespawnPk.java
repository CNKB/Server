package lkd.namsic.cnkb.domain.game.map;

import lkd.namsic.cnkb.domain.game.entity.Entity;
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
public class GameMapRespawnPk implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "location")
    GameMap gameMap;
    
    @ManyToOne
    @JoinColumn(name = "entity_id")
    Entity entity;
    
    @Override
    public int hashCode() {
        return gameMap.location.hashCode() ^ entity.getId().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GameMapRespawnPk pk) {
            return gameMap.location.equals(pk.gameMap.location) &&
                entity.getId().equals(pk.entity.getId());
        } else {
            return false;
        }
    }
    
}