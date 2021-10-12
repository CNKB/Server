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
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @CreationTimestamp
    @Column(nullable = false)
    LocalDateTime created;

    @Column(columnDefinition = "INT UNSIGNED NOT NULL")
    Long ip;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

}
