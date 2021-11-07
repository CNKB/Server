package lkd.namsic.cnkb.domain.game.entity;

import lkd.namsic.cnkb.domain.game.item.Equipment;
import lkd.namsic.cnkb.domain.game.map.GameMapFieldLivingEquip;
import lkd.namsic.cnkb.domain.game.player.Player;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivingEquip {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    
    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer limitLv = 1;
    
    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer lvDown = 0;
    
    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer totalReinforceCount = 0;
    
    @Builder.Default
    @Column(columnDefinition = "BIGINT UNSIGNED NOT NULL")
    BigInteger totalReinforceMoney = new BigInteger("0");
    
    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer reinforceCount = 0;
    
    @Builder.Default
    @Column(columnDefinition = "DOUBLE(3, 2) NOT NULL")
    Double reinforceFloor1 = 0D;
    
    @Builder.Default
    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer reinforceFloor2 = 0;
    
    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer lv = 1;
    
    @Builder.Default
    @Column(nullable = false)
    Long exp = 0L;
    
    @Builder.Default
    @Column(nullable = false)
    Boolean isEquipped = false;
    
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
    @OneToMany(mappedBy = "pk.livingEquip", cascade = CascadeType.ALL)
    List<GameMapFieldLivingEquip> gameMapFieldLivingEquipList = new ArrayList<>();
    
}