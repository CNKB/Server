package lkd.namsic.cnkb.domain.game.entity;

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
public class CreatedEntitySkill {
    
    @EmbeddedId
    CreatedEntitySkillPk pk;
    
    @Column(columnDefinition = "DOUBLE(3, 2) NOT NULL")
    Double percent;
    
}