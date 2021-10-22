package lkd.namsic.cnkb.domain.game.npc;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NpcChatLimitStatPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "npc_chat_id")
    NpcChat npcChat;

    @Column(columnDefinition = "TINYINT UNSIGNED")
    Integer statType;

    @Override
    public int hashCode() {
        return npcChat.id.hashCode() ^ statType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if (obj instanceof NpcChatLimitStatPk pk) {
            return npcChat.id.equals(pk.npcChat.id) &&
                    statType.equals(pk.statType);
        } else {
            return false;
        }
    }

}
