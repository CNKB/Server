package lkd.namsic.cnkb.domain;

import lkd.namsic.cnkb.domain.game.player.Player;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @CreationTimestamp
    @Column(nullable = false)
    LocalDateTime created;

    @UpdateTimestamp
    @Column(nullable = false)
    LocalDateTime updated;

    @Column
    LocalDateTime preventEndTime;

    @Builder.Default
    @Column(nullable = false)
    Boolean isBan = false;

    @Column
    String image;

    @Builder.Default
    @OneToMany(mappedBy = "pk.user", cascade = CascadeType.ALL)
    List<UserRole> userRoleList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<OAuth> oAuthList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Login> loginList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Player> playerList = new ArrayList<>();

}
