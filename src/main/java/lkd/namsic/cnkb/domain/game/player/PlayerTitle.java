package lkd.namsic.cnkb.domain.game.player;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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