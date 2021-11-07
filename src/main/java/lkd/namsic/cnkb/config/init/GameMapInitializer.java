package lkd.namsic.cnkb.config.init;

import lkd.namsic.cnkb.base.Location;
import lkd.namsic.cnkb.domain.game.map.GameMap;
import lkd.namsic.cnkb.enums.MapType;
import lkd.namsic.cnkb.repository.GameMapRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameMapInitializer extends Initializer {

    @Autowired
    GameMapRepository gameMapRepository;

    @Override
    protected void init() {
        createGameMap(1, 1, "시작의 마을", 1, MapType.CITY);
        createGameMap(2, 1, "조용한 바닷가", 1, MapType.SEA);
        createGameMap(2, 2, "모험의 평원", 1, MapType.FIELD);
    }

    private void createGameMap(int x, int y, @NonNull String mapName,
                               int requireLv, @NonNull MapType mapType) {
        int hex = Location.builder().x(x).y(y).build().toHex();

        if(gameMapRepository.findById(hex).isEmpty()) {
            GameMap.builder()
                    .location(hex)
                    .name(mapName)
                    .requireLv(requireLv)
                    .mapType(mapType.value)
                    .build();
        }
    }

}