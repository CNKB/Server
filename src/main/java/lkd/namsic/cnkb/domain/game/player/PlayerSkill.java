package lkd.namsic.cnkb.domain.game.player;

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
public class PlayerSkill {
    
    @EmbeddedId
    PlayerSkillPk pk;
    
    @Builder.Default
    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer lv = 1;
    
}