package lkd.namsic.cnkb.domain.game.living;

import lkd.namsic.cnkb.domain.game.entity.CreatedEntity;
import lkd.namsic.cnkb.domain.game.item.Equipment;
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
public class LivingEquipUnique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    Player player;

    @ManyToOne
    @JoinColumn(name = "created_entity_id")
    CreatedEntity createdEntity;
    
    @ManyToOne
    @JoinColumn(name = "equip_id", nullable = false)
    Equipment equipment;

    @Builder.Default
    @OneToMany(mappedBy = "living", cascade = CascadeType.ALL)
    List<LivingEquip> livingEquipList = new ArrayList<>();

}
