package lkd.namsic.cnkb.domain.game.chat;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column(nullable = false, length = 63)
    String message;

    @Builder.Default
    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer sequence = 0;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    Chat chat;

}
