package lkd.namsic.cnkb.domain.game.living;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer event;

    @ManyToOne
    @JoinColumn(name = "living_id", nullable = false, unique = true)
    LivingEventUnique living;

}