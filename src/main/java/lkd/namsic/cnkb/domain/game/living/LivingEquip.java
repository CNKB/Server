package lkd.namsic.cnkb.domain.game.living;

import lkd.namsic.cnkb.domain.game.item.Equipment;
import lkd.namsic.cnkb.domain.game.map.GameMapFieldLivingEquip;
import lombok.*;

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
    @JoinColumn(name = "living_id", nullable = false, unique = true)
    LivingEquipUnique living;

    @Builder.Default
    @OneToMany(mappedBy = "pk.livingEquip", cascade = CascadeType.ALL)
    List<GameMapFieldLivingEquip> gameMapFieldLivingEquipList = new ArrayList<>();

}