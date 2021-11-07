package lkd.namsic.cnkb.domain.game.map;

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
public class GameMapLimitQuest {
    
    @EmbeddedId
    GameMapLimitQuestPk pk;
    
    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer clearCount = 1;
    
}