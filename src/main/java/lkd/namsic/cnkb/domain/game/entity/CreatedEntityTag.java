package lkd.namsic.cnkb.domain.game.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatedEntityTag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    
    @Column(nullable = false, length = 63)
    String tag;
    
    @ManyToOne
    @JoinColumn(name = "created_entity_id", nullable = false)
    CreatedEntity createdEntity;
    
}