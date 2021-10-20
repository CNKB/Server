package lkd.namsic.cnkb.domain.game.item;

import lkd.namsic.cnkb.domain.game.player.Planted;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Plant {

    @Id
    @Column
    Long id;

    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer lv;

    @Column(columnDefinition = "INTEGER UNSIGNED NOT NULL")
    Long growTime;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id")
    Item item;

    @ManyToOne
    @JoinColumn(name = "reward_item_id", nullable = false)
    Item rewardItem;

    @Builder.Default
    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer rewardItemCount = 1;

    @Builder.Default
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    List<Planted> plantedList = new ArrayList<>();

}
