package lkd.namsic.cnkb.config.init;

import lkd.namsic.cnkb.base.Location;
import lkd.namsic.cnkb.domain.game.map.GameMap;
import lkd.namsic.cnkb.enums.MapType;
import lkd.namsic.cnkb.repository.GameMapRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameMapInitializer extends Initializer {

    private final GameMapRepository gameMapRepository;

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
            gameMapRepository.save(
                GameMap.builder()
                    .location(hex)
                    .name(mapName)
                    .requireLv(requireLv)
                    .mapType(mapType.value)
                    .build()
            );
        }
    }

}