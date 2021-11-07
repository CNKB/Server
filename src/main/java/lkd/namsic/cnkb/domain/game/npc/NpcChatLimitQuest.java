package lkd.namsic.cnkb.domain.game.npc;

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
public class NpcChatLimitQuest {
    
    @EmbeddedId
    NpcChatLimitQuestPk pk;
    
    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer minClear = 0;
    
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    Integer maxClear;
    
}