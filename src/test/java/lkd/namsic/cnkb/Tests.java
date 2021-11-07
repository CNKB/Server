package lkd.namsic.cnkb;

import static org.junit.jupiter.api.Assertions.*;

import lkd.namsic.cnkb.domain.game.entity.LivingVariable;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.enums.EquipType;
import lkd.namsic.cnkb.enums.NamedEnum;
import lkd.namsic.cnkb.enums.VariableType;
import lkd.namsic.cnkb.repository.LivingVariableRepository;
import lkd.namsic.cnkb.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unused")
@SpringBootTest
public class Tests {
    
    @Autowired
    private PlayerRepository playerRepository;
    
    @Autowired
    private LivingVariableRepository livingVariableRepository;
    
    @Test
    public void testEnumName() {
        assertEquals("equipType.heartGem", NamedEnum.getName(EquipType.HEART_GEM));
    }
    
    @Test
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void testJpa() {
        //noinspection OptionalGetWithoutIsPresent
        Player player = playerRepository.findById(1L).get();
        livingVariableRepository.save(
            LivingVariable.builder()
                .player(player)
                .variable(VariableType.MINE_LV.value)
                .data("1")
                .build()
        );
        
        System.out.println(player.getLivingVariableList().get(0).getVariable());
    }
    
}