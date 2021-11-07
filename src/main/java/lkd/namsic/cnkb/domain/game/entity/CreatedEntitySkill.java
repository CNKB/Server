package lkd.namsic.cnkb.domain.game.entity;

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
public class CreatedEntitySkill {
    
    @EmbeddedId
    CreatedEntitySkillPk pk;
    
    @Column(columnDefinition = "DOUBLE(3, 2) NOT NULL")
    Double percent;
    
}