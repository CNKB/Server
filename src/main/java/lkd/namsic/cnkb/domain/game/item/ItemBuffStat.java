package lkd.namsic.cnkb.domain.game.item;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
public class ItemBuffStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false)
    Integer time;

    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer statType;

    @Column(nullable = false)
    Integer stat;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    Item item;

}
