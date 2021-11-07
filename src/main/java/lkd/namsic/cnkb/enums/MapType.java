package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

public enum MapType implements NamedEnum {
    
    CITY(1),
    FIELD(2),
    MOUNTAIN(3),
    FOREST(4),
    RIVER(5),
    CEMETERY(6),
    CAVE(7),
    CLIFF(8),
    SWAMP(9),
    HILL(10),
    FOG(11),
    SEA(12),
    SINKHOLE(13),
    UNDERGROUND_CITY(14),
    ABANDONED_PLACE(15),
    CORRUPTED_RIVER(16),
    HUGE_CAVE(17),
    POISON_SWAMP(18),
    ANCIENT_CEMETERY(19),
    VOLCANO(20),
    HELL(21),
    HEAVEN(22),
    FAR_SIDE(23);
    
    public static final EnumFinder<MapType> finder = EnumFinder.getFinder(MapType.values());
    public final int value;
    
    MapType(int value) {
        this.value = value;
    }
    
    @NonNull
    @Override
    public String getBase() {
        return "mapType";
    }
    
}