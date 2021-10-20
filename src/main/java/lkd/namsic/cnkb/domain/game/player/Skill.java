package lkd.namsic.cnkb.domain.game.player;

import lkd.namsic.cnkb.domain.game.entity.CreatedEntitySkill;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false, length = 63, unique = true)
    String name;

    @Column(nullable = false, length = 63, unique = true)
    String passiveDes;

    @Column(nullable = false, length = 63, unique = true)
    String activeDes;

    @Builder.Default
    @OneToMany(mappedBy = "pk.skill", cascade = CascadeType.ALL)
    List<PlayerSkill> playerSkillList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.skill", cascade = CascadeType.ALL)
    List<CreatedEntitySkill> createdEntitySkillList = new ArrayList<>();

}
