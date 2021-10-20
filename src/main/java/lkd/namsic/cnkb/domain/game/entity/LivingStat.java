package lkd.namsic.cnkb.domain.game.entity;

import lkd.namsic.cnkb.domain.game.player.Player;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
public class LivingStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer statSaveType;

    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer statType;

    @Column(nullable = false)
    Integer stat;

    @ManyToOne
    @JoinColumn(name = "player_id")
    Player player;

    @ManyToOne
    @JoinColumn(name = "created_entity_id")
    CreatedEntity createdEntity;

}
