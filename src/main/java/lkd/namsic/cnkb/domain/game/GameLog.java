package lkd.namsic.cnkb.domain.game;

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
public class GameLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    
    @Column(nullable = false, length = 31)
    String name;
    
    @Column(nullable = false)
    String value;
    
}