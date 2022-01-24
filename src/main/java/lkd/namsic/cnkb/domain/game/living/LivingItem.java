package lkd.namsic.cnkb.domain.game.living;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false)
    Integer itemCount;

    @ManyToOne
    @JoinColumn(name = "living_id", nullable = false, unique = true)
    LivingItemUnique living;

}