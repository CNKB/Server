package lkd.namsic.cnkb.handler;

import lkd.namsic.cnkb.domain.game.living.LivingVariable;
import lkd.namsic.cnkb.domain.game.living.LivingVariableUnique;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.enums.VariableType;
import lkd.namsic.cnkb.repository.LivingVariableRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlayerVariableHandler {

    private final LivingVariableRepository livingVariableRepository;

    public String getVariableString(@NonNull Player player, @NonNull VariableType variableType, String defaultValue) {
        Optional<LivingVariable> optional = livingVariableRepository.findByLivingAndVariable(
            LivingVariableUnique.builder().player(player).build(), variableType.value
        );

        if(optional.isEmpty()) {
            return defaultValue;
        } else {
            return optional.get().getData();
        }
    }

    public int getVariable(@NonNull Player player, @NonNull VariableType variableType) {
        return this.getVariable(player, variableType, 0);
    }

    public int getVariable(@NonNull Player player, @NonNull VariableType variableType, int defaultValue) {
        String variableString = this.getVariableString(player, variableType, String.valueOf(defaultValue));

        try {
            return Integer.parseInt(variableString);
        } catch(Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

}