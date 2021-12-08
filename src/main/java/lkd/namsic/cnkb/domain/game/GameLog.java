package lkd.namsic.cnkb.domain.game;

import lkd.namsic.cnkb.domain.User;
import lkd.namsic.cnkb.domain.game.entity.CreatedEntity;
import lkd.namsic.cnkb.domain.game.player.Player;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    
    @CreationTimestamp
    @Column(nullable = false)
    LocalDateTime created;
    
    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer logType;
    
    @Column(nullable = false)
    String log;
    
    @ManyToOne
    @JoinColumn
    User user;
    
    @ManyToOne
    @JoinColumn
    Player player;
    
    @ManyToOne
    @JoinColumn
    CreatedEntity createdEntity;
    
}