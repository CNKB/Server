package lkd.namsic.cnkb.domain.game.living;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivingStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer statSaveType;

    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer statType;

    @Column(nullable = false)
    Integer stat;

    @ManyToOne
    @JoinColumn(name = "living_id", nullable = false, unique = true)
    LivingStatUnique living;

}