package lkd.namsic.cnkb.domain.game.quest;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestRewardEquip {
    
    @EmbeddedId
    QuestRewardEquipPk pk;
    
    @Builder.Default
    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer equipCount = 1;
    
}