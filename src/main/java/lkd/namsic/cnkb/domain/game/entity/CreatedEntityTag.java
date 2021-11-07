package lkd.namsic.cnkb.domain.game.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
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