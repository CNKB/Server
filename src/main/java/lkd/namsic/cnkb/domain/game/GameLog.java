package lkd.namsic.cnkb.domain.game;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
