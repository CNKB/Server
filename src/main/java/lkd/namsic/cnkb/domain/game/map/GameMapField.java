package lkd.namsic.cnkb.domain.game.map;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class GameMapField {
    
    @Id
    @Column
    Integer fieldLocation;
    
    @Column(columnDefinition = "SMALLINT UNSIGNED")
    Integer arriveEvent;
    
    @Column(columnDefinition = "BIGINT UNSIGNED")
    BigInteger money;
    
    @ManyToOne
    @JoinColumn(name = "location", nullable = false)
    GameMap gameMap;
    
    @Builder.Default
    @OneToMany(mappedBy = "pk.gameMapField", cascade = CascadeType.ALL)
    List<GameMapFieldItem> gameMapFieldItemList = new ArrayList<>();
    
    @Builder.Default
    @OneToMany(mappedBy = "pk.gameMapField", cascade = CascadeType.ALL)
    List<GameMapFieldLivingEquip> gameMapFieldLivingEquipList = new ArrayList<>();
    
}