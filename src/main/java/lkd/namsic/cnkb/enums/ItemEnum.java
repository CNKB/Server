package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

public enum ItemEnum implements NamedEnum {
    
    STONE(1),
    COAL(2),
    QUARTZ(3),
    COPPER(4),
    TIN(5),
    IRON(6),
    BLUE_STONE(7),
    RED_STONE(8),
    SILVER(9),
    HARD_COAL(10),
    TITANIUM(11),
    BLACK_QUARTZ(12),
    WHITE_QUARTZ(13),
    ORIHARCON(14),
    YIN_YANG_STONE(15),
    AITUME(16),
    LANDIUM(17);
    
    public static final EnumFinder<ItemEnum> finder = EnumFinder.getFinder(ItemEnum.values());
    public final int value;
    
    ItemEnum(int value) {
        this.value = value;
    }
    
    @NonNull
    @Override
    public String getBase() {
        return "itemEnum";
    }

}
