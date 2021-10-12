package lkd.namsic.cnkb.domain.game.chat;

import lkd.namsic.cnkb.domain.game.npc.Npc;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer response;

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
