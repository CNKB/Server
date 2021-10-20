package lkd.namsic.cnkb.domain.game.quest;

import lkd.namsic.cnkb.domain.game.item.Equipment;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
public class QuestRewardEquipPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "quest_id")
    Quest quest;

    @ManyToOne
    @JoinColumn(name = "equip_id")
    Equipment equipment;

    @Override
    public int hashCode() {
        return quest.id.hashCode() ^ equipment.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if (obj instanceof QuestRewardEquipPk pk) {
            return quest.id.equals(pk.quest.id) &&
                    equipment.getId().equals(pk.equipment.getId());
        } else {
            return false;
        }
    }

}
