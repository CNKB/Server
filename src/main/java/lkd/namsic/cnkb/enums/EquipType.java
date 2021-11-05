package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

public enum EquipType implements NamedEnum {

    WEAPON(1),
    HELMET(2),
    CHESTPLATE(3),
    LEGGINGS(4),
    SHOES(5),
    RINGS(6),
    GEM1(7),
    GEM2(8),
    GEM3(9),
    HEART_GEM(10),
    AMULET(11);

    public static final EnumFinder<EquipType> finder = EnumFinder.getFinder(EquipType.values());
    public final int value;

    EquipType(int value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String getBase() {
        return "equipType";
    }

}
