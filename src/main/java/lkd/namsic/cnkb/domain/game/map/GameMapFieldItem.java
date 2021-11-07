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
public class GameMapFieldItem {
    
    @EmbeddedId
    GameMapFieldItemPk pk;
    
    @Column(nullable = false)
    Integer itemCount;
    
}