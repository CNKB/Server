package lkd.namsic.cnkb.domain.game.living;

import lkd.namsic.cnkb.domain.game.entity.CreatedEntity;
import lkd.namsic.cnkb.domain.game.player.Player;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivingEventUnique {

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

    @Builder.Default
    @OneToMany(mappedBy = "living", cascade = CascadeType.ALL)
    List<LivingEvent> livingEventList = new ArrayList<>();

}
