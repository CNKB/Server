package lkd.namsic.cnkb.domain.game.item;

import lkd.namsic.cnkb.domain.game.quest.QuestRewardEquipRecipe;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipRecipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;
    
    @Builder.Default
    @Column(columnDefinition = "TINYINT UNSIGNED NOT NULL")
    Integer recipe = 1;
    
    @Column
    Integer recipeItemCount;
    
    @Column(columnDefinition = "TINYINT UNSIGNED")
    Integer recipeEquipMinReinforce;
    
    @Column(columnDefinition = "TINYINT UNSIGNED")
    Integer recipeEquipMaxReinforce;
    
    @Column(columnDefinition = "TINYINT UNSIGNED")
    Integer recipeEquipCount;
    
    @ManyToOne
    @JoinColumn(name = "equip_id", nullable = false)
    Equipment equipment;
    
    @ManyToOne
    @JoinColumn(name = "recipe_equip_id")
    Equipment recipeEquip;
    
    @ManyToOne
    @JoinColumn(name = "recipe_item_id")
    Item recipeItem;
    
    @Builder.Default
    @OneToMany(mappedBy = "pk.equipRecipe", cascade = CascadeType.ALL)
    List<QuestRewardEquipRecipe> questRewardEquipRecipeList = new ArrayList<>();
    
}