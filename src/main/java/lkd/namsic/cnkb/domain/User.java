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

    @Column(nullable = false, length = 320)
    String email;

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

    @Column
    String token;

    @Column(columnDefinition = "BIGINT UNSIGNED NOT NULL")
    Long lastIp;

    @Builder.Default
    @OneToMany(mappedBy = "pk.user", cascade = CascadeType.ALL)
    List<UserRole> userRoleList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<SignIn> signInList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Player> playerList = new ArrayList<>();

}
