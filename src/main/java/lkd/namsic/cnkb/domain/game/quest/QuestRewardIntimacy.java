package lkd.namsic.cnkb.domain.game.quest;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestRewardIntimacy {

    @EmbeddedId
    QuestRewardIntimacyPk pk;

    @Column(columnDefinition = "SMALLINT UNSIGNED NOT NULL")
    Integer intimacy;

}
