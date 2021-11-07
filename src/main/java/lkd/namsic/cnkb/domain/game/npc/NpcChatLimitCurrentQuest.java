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
public class NpcChatLimitCurrentQuest {
    
    @EmbeddedId
    NpcChatLimitCurrentQuestPk pk;
    
    @Builder.Default
    @Column(nullable = false)
    Boolean shouldRun = false;
    
}