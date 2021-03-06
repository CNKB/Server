package lkd.namsic.cnkb.domain.game.quest;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestRewardItemRecipe {
    
    @EmbeddedId
    QuestRewardItemRecipePk pk;
    
}