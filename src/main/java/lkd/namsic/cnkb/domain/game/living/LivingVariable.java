package lkd.namsic.cnkb.domain.game.living;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivingVariable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer variable;

    @Column(columnDefinition = "JSON NOT NULL")
    String data;

    @ManyToOne
    @JoinColumn(name = "living_id", nullable = false, unique = true)
    LivingVariableUnique living;

}