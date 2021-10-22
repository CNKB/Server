package lkd.namsic.cnkb.domain.game.map;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameMap {

    @Id
    @Column
    Integer location;

    @Column(nullable = false, length = 63, unique = true)
    String name;

    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer requireLv = 1;

    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer mapType;

    @Builder.Default
    @OneToMany(mappedBy = "pk.gameMap", cascade = CascadeType.ALL)
    List<GameMapRespawn> gameMapRespawnList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "gameMap", cascade = CascadeType.ALL)
    List<GameMapField> gameMapFieldList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.gameMap", cascade = CascadeType.ALL)
    List<GameMapLimitQuest> gameMapLimitQuestList = new ArrayList<>();

}
