package lkd.namsic.cnkb.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false)
    String accessToken;

    @Builder.Default
    @Column(nullable = false, length = 15)
    String provider = "google";

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

}
