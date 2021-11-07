package lkd.namsic.cnkb.config.init;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class GameInitializer {
    
    private final RoleInitializer roleInitializer;
    private final GameMapInitializer gameMapInitializer;
    
    public void initDb() {
        log.info("Initializing Roles");
        roleInitializer.init();
        
        log.info("Initializing GameMaps");
        gameMapInitializer.init();
    }
    
}