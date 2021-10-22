package lkd.namsic.cnkb.domain.game.player;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false, length = 63)
    String title;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    Player player;

}
