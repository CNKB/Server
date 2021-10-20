package lkd.namsic.cnkb.domain.game.entity;

import lkd.namsic.cnkb.domain.game.player.Player;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
public class LivingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer event;

    @ManyToOne
    @JoinColumn(name = "player_id")
    Player player;

    @ManyToOne
    @JoinColumn(name = "created_entity_id")
    CreatedEntity createdEntity;

}
