package lkd.namsic.cnkb.handler.mnm;

import lkd.namsic.cnkb.domain.game.living.LivingVariable;
import lkd.namsic.cnkb.domain.game.living.LivingVariableUnique;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.enums.VariableType;
import lkd.namsic.cnkb.repository.LivingVariableRepository;
import lkd.namsic.cnkb.repository.LivingVariableUniqueRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class PlayerVariableHandler {

    private final LivingVariableRepository livingVariableRepository;
    private final LivingVariableUniqueRepository livingVariableUniqueRepository;

    @NonNull
    public String getVariable(@NonNull Player player, @NonNull VariableType variableType,
                              @NonNull String defaultValue) {
        Optional<LivingVariableUnique> optionalUnique = livingVariableUniqueRepository.findByPlayerAndVariable(
            player, variableType.value
        );

        if(optionalUnique.isEmpty()) {
            return defaultValue;
        } else {
            Optional<LivingVariable> optionalVariable = livingVariableRepository.findByLiving(
                optionalUnique.get()
            );

            if(optionalVariable.isEmpty()) {
                return defaultValue;
            } else {
                return optionalVariable.get().getData();
            }
        }
    }
    
    public int getVariable(@NonNull Player player, @NonNull VariableType variableType) {
        return this.getVariable(player, variableType, 0);
    }
    
    public int getVariable(@NonNull Player player, @NonNull VariableType variableType, int defaultValue) {
        String value = this.getVariable(player, variableType, String.valueOf(defaultValue));
        
        try {
            return Integer.parseInt(value);
        } catch(Exception e) {
            return defaultValue;
        }
    }
    
    @Nullable
    public String setVariable(@NonNull Player player, @NonNull VariableType variableType, @NonNull String value) {
        LivingVariableUnique unique;
        
        int variable = variableType.value;
        Optional<LivingVariableUnique> optionalUnique = livingVariableUniqueRepository.findByPlayerAndVariable(
            player, variable
        );
        
        if(optionalUnique.isEmpty()) {
            unique = livingVariableUniqueRepository.save(
                LivingVariableUnique
                    .builder()
                    .player(player)
                    .variable(variable)
                    .build()
            );
            
            livingVariableRepository.save(
                LivingVariable.builder()
                    .living(unique)
                    .data(value)
                    .build()
            );
            
            return null;
        } else {
            unique = optionalUnique.get();
            Optional<LivingVariable> optionalLivingVariable = livingVariableRepository.findByLiving(
                unique
            );
            
            livingVariableRepository.save(
                LivingVariable.builder()
                    .living(unique)
                    .data(value)
                    .build()
            );
            
            if(optionalLivingVariable.isEmpty()) {
                return null;
            } else {
                return optionalLivingVariable.get().getData();
            }
        }
    }

}