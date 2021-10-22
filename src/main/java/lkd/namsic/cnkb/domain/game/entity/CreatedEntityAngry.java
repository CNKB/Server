package lkd.namsic.cnkb.domain.game.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatedEntityAngry {

    @EmbeddedId
    CreatedEntityAngryPk pk;

    @Column(nullable = false)
    LocalDateTime angryTime;

}
