package lkd.namsic.cnkb.domain.game.entity;

import lkd.namsic.cnkb.domain.game.item.Item;
import lkd.namsic.cnkb.domain.game.player.Player;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivingItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    
    @Column(nullable = false)
    Integer itemCount;
    
    @ManyToOne
    @JoinColumn(name = "player_id")
    Player player;
    
    @ManyToOne
    @JoinColumn(name = "created_entity_id")
    CreatedEntity createdEntity;
    
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    Item item;
    
}