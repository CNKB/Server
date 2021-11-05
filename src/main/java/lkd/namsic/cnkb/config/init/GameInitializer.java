package lkd.namsic.cnkb.config.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GameInitializer {

    @Autowired RoleInitializer roleInitializer;
    @Autowired GameMapInitializer gameMapInitializer;

    public void initDb() {
        log.info("Initializing Roles");
        roleInitializer.init();

        log.info("Initializing GameMaps");
        gameMapInitializer.init();
    }

}
