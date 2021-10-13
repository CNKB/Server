package lkd.namsic.cnkb.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @CreationTimestamp
    @Column(nullable = false)
    LocalDateTime created;

    @Column(columnDefinition = "INT UNSIGNED NOT NULL")
    Long ip;

    @Column(nullable = false, length = 31)
    String provider;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

}
