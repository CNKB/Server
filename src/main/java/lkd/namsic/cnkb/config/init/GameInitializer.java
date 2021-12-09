package lkd.namsic.cnkb.config.init;

import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.enums.Doing;
import lkd.namsic.cnkb.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameInitializer {
    
    private final PlayerRepository playerRepository;
    private final Initializer[] initializers;
    
    public void initDb() {
        List<Player> playerList = playerRepository.findAll();
        for(Player player : playerList) {
            if(player.getDoing() != Doing.NONE.value) {
                player.setDoing(Doing.NONE.value);
                playerRepository.save(player);
            }
        }
        
        for(Initializer initializer : initializers) {
            log.info("Initializing " + initializer.getName() + "s");
            initializer.init();
        }
    }
    
}