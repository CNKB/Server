package lkd.namsic.cnkb.domain.game.quest;

import lkd.namsic.cnkb.domain.game.item.ItemRecipe;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestRewardItemRecipePk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "quest_id")
    Quest quest;

    @ManyToOne
    @JoinColumn(name = "item_recipe_id")
    ItemRecipe itemRecipe;

    @Override
    public int hashCode() {
        return quest.id.hashCode() ^ itemRecipe.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if (obj instanceof QuestRewardItemRecipePk pk) {
            return quest.id.equals(pk.quest.id) &&
                    itemRecipe.getId().equals(pk.itemRecipe.getId());
        } else {
            return false;
        }
    }

}
