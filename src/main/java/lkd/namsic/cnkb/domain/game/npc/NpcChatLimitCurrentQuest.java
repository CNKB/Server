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
public class NpcChatLimitCurrentQuest {
    
    @EmbeddedId
    NpcChatLimitCurrentQuestPk pk;
    
    @Builder.Default
    @Column(nullable = false)
    Boolean shouldRun = false;
    
}