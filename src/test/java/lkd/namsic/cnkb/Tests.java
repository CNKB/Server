package lkd.namsic.cnkb;

import lkd.namsic.cnkb.enums.EquipType;
import lkd.namsic.cnkb.enums.NamedEnum;
import lkd.namsic.cnkb.repository.LivingVariableRepository;
import lkd.namsic.cnkb.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    
}