package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

public enum EquipType {

    WEAPON("equipType.weapon", 1),
    HELMET("equipType.helmet", 2),
    CHESTPLATE("equipType.chestplate", 3),
    LEGGINGS("equipType.leggings", 4),
    SHOES("equipType.shoes", 5),
    RINGS("equipType.rings", 6),
    GEM1("equipType.gem1", 7),
    GEM2("equipType.gem2", 8),
    GEM3("equipType.gem3", 9),
    HEART_GEM("equipType.heartGem", 10),
    AMULET("equipType.amulet", 11);

    @NonNull
    public final String displayName;

    public final int value;

    EquipType(@NonNull String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

    @NonNull
    public static EquipType findByName(@NonNull String name) {
        for(EquipType equipType : EquipType.values()) {
            if(equipType.displayName.equals(name)) {
                return equipType;
            }
        }

        throw new RuntimeException("equipType.unknown");
    }

}
