package lkd.namsic.cnkb.domain.game.map;

import lkd.namsic.cnkb.domain.game.living.LivingEquip;
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
public class GameMapFieldLivingEquipPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "field_location")
    GameMapField gameMapField;

    @ManyToOne
    @JoinColumn(name = "living_equip_id")
    LivingEquip livingEquip;

    @Override
    public int hashCode() {
        return gameMapField.gameMap.location.hashCode() ^ livingEquip.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj instanceof GameMapFieldLivingEquipPk pk) {
            return gameMapField.gameMap.location.equals(pk.gameMapField.gameMap.location) &&
                livingEquip.getId().equals(pk.livingEquip.getId());
        } else {
            return false;
        }
    }

}