package lkd.namsic.cnkb.domain.game.entity;

import lkd.namsic.cnkb.domain.game.chat.Chat;
import lkd.namsic.cnkb.domain.game.map.GameMapRespawn;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Getter
@Setter
@Builder
public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false, length = 63, unique = true)
    String name;

    @Column(nullable = false, length = 63, unique = true)
    String des;

    @ManyToOne
    @JoinColumn(name = "spawn_chat_id", nullable = false)
    Chat spawnChat;

    @ManyToOne
    @JoinColumn(name = "fight_chat_id", nullable = false)
    Chat fightChat;

    @Builder.Default
    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL)
    List<CreatedEntity> createdEntityList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.entity", cascade = CascadeType.ALL)
    List<GameMapRespawn> gameMapRespawnList = new ArrayList<>();

}
