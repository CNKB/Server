package lkd.namsic.cnkb.domain.game.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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