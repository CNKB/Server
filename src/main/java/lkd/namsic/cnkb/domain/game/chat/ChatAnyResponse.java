package lkd.namsic.cnkb.domain.game.chat;

import lkd.namsic.cnkb.domain.game.npc.Npc;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
public class ChatAnyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false, length = 63)
    String anyResponse;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    Chat chat;

    @ManyToOne
    @JoinColumn(name = "target_chat_id", nullable = false)
    Chat targetChat;

    @ManyToOne
    @JoinColumn(name = "npc_id")
    Npc npc;

}
