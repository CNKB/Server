package lkd.namsic.cnkb.domain.game.entity;

import lkd.namsic.cnkb.enums.Doing;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Getter
@Setter
@Builder
public class CreatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @CreationTimestamp
    @Column(nullable = false)
    LocalDateTime created;

    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer lv = 1;

    @Builder.Default
    @Column(nullable = false)
    Long exp = 0L;

    @Builder.Default
    @Column(columnDefinition = "BIGINT UNSIGNED NOT NULL")
    BigInteger money = new BigInteger("0");

    @Builder.Default
    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer doing = Doing.NONE.value;

    @Column(nullable = false)
    Integer location;

    @ManyToOne
    @JoinColumn(name = "entity_id", nullable = false)
    Entity entity;

    @Builder.Default
    @OneToMany(mappedBy = "pk.createdEntity", cascade = CascadeType.ALL)
    List<CreatedEntitySkill> createdEntitySkillList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.createdEntity", cascade = CascadeType.ALL)
    List<CreatedEntityAngry> createdEntityAngryList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "createdEntity", cascade = CascadeType.ALL)
    List<LivingEquip> livingEquipList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "createdEntity", cascade = CascadeType.ALL)
    List<LivingEvent> livingEventList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "createdEntity", cascade = CascadeType.ALL)
    List<LivingItem> livingItemList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "createdEntity", cascade = CascadeType.ALL)
    List<LivingStat> livingStatList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "createdEntity", cascade = CascadeType.ALL)
    List<LivingVariable> livingVariableList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "createdEntity", cascade = CascadeType.ALL)
    List<CreatedEntityTag> createdEntityTagList = new ArrayList<>();

}
