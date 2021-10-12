package lkd.namsic.cnkb.domain.game.player;

import lkd.namsic.cnkb.domain.game.entity.*;
import lkd.namsic.cnkb.enums.Doing;
import lkd.namsic.cnkb.domain.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false, length = 20, unique = true)
    String name;

    @CreationTimestamp
    @Column(nullable = false)
    LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    LocalDateTime updated;

    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer lv = 1;

    @Builder.Default
    @Column(nullable = false)
    Long exp = 0L;

    @Builder.Default
    @Column(nullable = false)
    Integer sp = 0;

    @Builder.Default
    @Column(columnDefinition = "BIGINT UNSIGNED NOT NULL")
    BigInteger money = new BigInteger("0");

    @Builder.Default
    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer doing = Doing.NONE.value;

    @Builder.Default
    @Column(nullable = false)
    Integer location = 0x01010101;

    @Builder.Default
    @Column(nullable = false)
    Integer baseLocation = 0x01010101;
    
    @Builder.Default
    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer farmLv = 0;
    
    @Builder.Default
    @Column(nullable = false, length = 63)
    String title = "초심자";

    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer continueDay = 0;

    @Builder.Default
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer adv = 0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Builder.Default
    @OneToMany(mappedBy = "pk.player", cascade = CascadeType.ALL)
    List<PlayerSkill> playerSkillList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    List<PlayerTitle> playerTitleList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    List<Planted> plantedList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pk.player", cascade = CascadeType.ALL)
    List<CreatedEntityAngry> createdEntityAngryList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    List<LivingEquip> livingEquipList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    List<LivingEvent> livingEventList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    List<LivingItem> livingItemList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    List<LivingStat> livingStatList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    List<LivingVariable> livingVariableList = new ArrayList<>();

}
