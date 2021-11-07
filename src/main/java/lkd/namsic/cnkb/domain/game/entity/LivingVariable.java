package lkd.namsic.cnkb.domain.game.entity;

import lkd.namsic.cnkb.domain.game.player.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class LivingVariable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer variable;
    
    @Column(columnDefinition = "JSON NOT NULL")
    String data;
    
    @ManyToOne
    @JoinColumn(name = "player_id")
    Player player;
    
    @ManyToOne
    @JoinColumn(name = "created_entity_id")
    CreatedEntity createdEntity;
    
}