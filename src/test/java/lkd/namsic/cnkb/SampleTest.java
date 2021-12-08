package lkd.namsic.cnkb;

import lkd.namsic.cnkb.config.GameConfig;
import lkd.namsic.cnkb.domain.game.living.LivingVariable;
import lkd.namsic.cnkb.domain.game.living.LivingVariableUnique;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.enums.EquipType;
import lkd.namsic.cnkb.enums.NamedEnum;
import lkd.namsic.cnkb.enums.VariableType;
import lkd.namsic.cnkb.enums.object.GameMapEnum;
import lkd.namsic.cnkb.enums.object.ItemEnum;
import lkd.namsic.cnkb.handler.MtMHandler;
import lkd.namsic.cnkb.repository.LivingVariableRepository;
import lkd.namsic.cnkb.repository.LivingVariableUniqueRepository;
import lkd.namsic.cnkb.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SampleTest {
    
    @Autowired private PlayerRepository playerRepository;
    @Autowired private LivingVariableRepository livingVariableRepository;
    @Autowired private LivingVariableUniqueRepository livingVariableUniqueRepository;
    
    @Autowired private GameConfig gameConfig;
    
    @Test
    public void testEnumName() {
        assertEquals("equipType.heartGem", NamedEnum.getName(EquipType.HEART_GEM));
    }
    
    @Test
    @Rollback(false)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void testTransaction() throws Exception {
        System.out.println("Before start - " + playerRepository.findById(1L).orElseThrow().getAdv());
        
        Thread thread1 = new Thread(() -> {
            try {
                Player player = playerRepository.findById(1L).orElseThrow();
                System.out.println(player);
                Thread.sleep(10);
                
                player.setAdv(1);
                playerRepository.save(player);
    
                System.out.println("After thread1 - " + player.getAdv());
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        
        Thread thread2 = new Thread(() -> {
            try {
                Player player = playerRepository.findById(1L).orElseThrow();
                System.out.println(player);
                thread1.join();
    
                System.out.println("Before thread2 - " + player.getAdv());
                System.out.println("saved - " + playerRepository.findById(1L).orElseThrow().getAdv());
                
                player.setAdv(2);
                playerRepository.save(player);
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        
        thread1.start();
        thread2.start();
        thread2.join();
        
        Thread.sleep(10);
        
        Player player = playerRepository.findById(1L).orElseThrow();
        System.out.println("Result - " + player.getAdv());
        
        player.setAdv(0);
        playerRepository.save(player);
    }
    
    @Test
    public void testFinder() {
        System.out.println(GameMapEnum.find(1, 1));
        System.out.println(ItemEnum.finder.find(3L));
    }
    
    @Test
    public void testMine() {
        List<Double> minePercentList = gameConfig.MINE_PERCENT.get(1);
        Random random = new Random();
        
        for(int i = 0; i < 100; i++) {
            double randomValue = random.nextDouble();
            double stackValue = 0;
    
            double minePercent;
            int size = minePercentList.size();
            for(int index = 0; index < size; index++) {
                minePercent = minePercentList.get(index);
        
                if(stackValue + minePercent > randomValue) {
                    System.out.println(gameConfig.MINE_ITEM_ID.get(index) + " " + randomValue);
                    break;
                } else {
                    stackValue += minePercent;
                }
            }
        }
    }
    
    @Test
    public void manyToManyTest() {
        MtMHandler<LivingVariable, LivingVariableUnique> playerVariableHandler = new MtMHandler<>();
        Player player = playerRepository.findById(1L).orElseThrow();
        
        int result = playerVariableHandler.getVariable(
            player, VariableType.MINE_LV, 1,
            livingVariableUniqueRepository::findByPlayer,
            (living, variable) -> livingVariableRepository.findByLivingAndVariable(living, variable),
            LivingVariable::getData
        );
        System.out.println(result);
    }
    
}