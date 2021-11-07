package lkd.namsic.cnkb.domain.game.quest;

import lkd.namsic.cnkb.domain.game.item.EquipRecipe;
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
public class QuestRewardEquipRecipePk implements Serializable {
    
    @ManyToOne
    @JoinColumn(name = "quest_id")
    Quest quest;
    
    @ManyToOne
    @JoinColumn(name = "equip_recipe_id")
    EquipRecipe equipRecipe;
    
    @Override
    public int hashCode() {
        return quest.id.hashCode() ^ equipRecipe.getId().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        
        if(obj instanceof QuestRewardEquipRecipePk pk) {
            return quest.id.equals(pk.quest.id) &&
                equipRecipe.getId().equals(pk.equipRecipe.getId());
        } else {
            return false;
        }
    }
    
}