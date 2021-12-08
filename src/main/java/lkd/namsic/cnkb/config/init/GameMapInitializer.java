package lkd.namsic.cnkb.config.init;

import lkd.namsic.cnkb.domain.game.map.GameMap;
import lkd.namsic.cnkb.enums.object.GameMapEnum;
import lkd.namsic.cnkb.repository.GameMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameMapInitializer extends Initializer {

    private final GameMapRepository gameMapRepository;
    
    @Override
    protected String getName() {
        return "GameMap";
    }

    @Override
    protected void init() {
        for(GameMapEnum gameMap : GameMapEnum.values()) {
            gameMapRepository.save(
                GameMap.builder()
                    .location(gameMap.location)
                    .requireLv(gameMap.requireLv)
                    .mapType(gameMap.mapType.value)
                    .build()
            );
        }
    }

}