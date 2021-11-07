package lkd.namsic.cnkb.domain.game.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class EquipEvent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer event;
    
    @ManyToOne
    @JoinColumn(name = "equip_id", nullable = false)
    Equipment equipment;
    
}