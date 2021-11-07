package lkd.namsic.cnkb.domain.game.map;

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
public class GameMapRespawn {
    
    @EmbeddedId
    GameMapRespawnPk pk;
    
    @Builder.Default
    @Column(columnDefinition = "DOUBLE(3, 2) NOT NULL")
    Double spawnPercent = 1D;
    
    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer minSpawn;
    
    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer maxSpawn;
    
}