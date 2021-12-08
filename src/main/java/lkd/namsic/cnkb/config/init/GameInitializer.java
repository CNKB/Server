package lkd.namsic.cnkb.config.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GameInitializer {
    
    private final Initializer[] initializers;
    
    public void initDb() {
        for(Initializer initializer : initializers) {
            log.info("Initializing " + initializer.getName() + "s");
            initializer.init();
        }
    }
    
}