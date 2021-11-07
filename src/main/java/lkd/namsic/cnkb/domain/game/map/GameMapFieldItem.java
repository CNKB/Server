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
public class GameMapFieldItem {
    
    @EmbeddedId
    GameMapFieldItemPk pk;
    
    @Column(nullable = false)
    Integer itemCount;
    
}