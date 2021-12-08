package lkd.namsic.cnkb.enums.object;

import lkd.namsic.cnkb.base.Location;
import lkd.namsic.cnkb.enums.EnumFinder;
import lkd.namsic.cnkb.enums.MapType;
import lkd.namsic.cnkb.enums.NamedEnum;
import lkd.namsic.cnkb.enums.ValuedEnum;
import org.springframework.lang.NonNull;

public enum GameMapEnum implements NamedEnum, ValuedEnum<Integer> {
    START_VILLAGE(1, 1, 1, MapType.VILLAGE),
    QUIET_BEACH(2, 1, 1, MapType.SEA),
    ADVENTURE_FIELD(2, 2, 1, MapType.FIELD);
    
    public static final EnumFinder<Integer, GameMapEnum> finder =
        EnumFinder.getFinder(GameMapEnum.values());
    
    public final int location;
    public final int requireLv;
    public final MapType mapType;
    
    GameMapEnum(int x, int y, int requireLv, @NonNull MapType mapType) {
        this.location = Location.toHex(x, y);
        this.requireLv = requireLv;
        this.mapType = mapType;
    }
    
    @NonNull
    @Override
    public String getBase() {
        return "map";
    }
    
    @Override
    public Integer getValue() { return this.location; }
    
    @NonNull
    public static GameMapEnum find(int x, int y) {
        return finder.find(Location.toHex(x, y));
    }
    
}
