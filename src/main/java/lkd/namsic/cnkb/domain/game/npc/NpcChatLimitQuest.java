package lkd.namsic.cnkb.domain.game.npc;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class NpcChatLimitQuest {
    
    @EmbeddedId
    NpcChatLimitQuestPk pk;
    
    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer minClear = 0;
    
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    Integer maxClear;
    
}