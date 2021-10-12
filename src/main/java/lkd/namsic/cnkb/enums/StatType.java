package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

public enum StatType {

    MAXHP("최대 체력", 1, 1),
    HP("현재 체력", 0, 2),
    MAXMN("최대 마나", 3, 3),
    MN("현재 마나", 0, 4),
    ATK("공격력", 5, 5),
    MATK("마법 공격력", 5, 6),
    AGI("민첩", 1, 7),
    ATS("공격속도", 1, 8),
    DEF("방어력", 3, 9),
    MDEF("마법 방어력", 3, 10),
    BRE("방어 관통력", 2, 11),
    MBRE("마법 방어 관통력", 2, 12),
    DRA("흡수력", 5, 13),
    MDRA("마법 흡수력", 5, 14),
    EVA("회피", 1, 15),
    ACC("정확도", 1, 16);

    @NonNull
    public final String displayName;

    public final int useSp;

    public final int value;

    StatType(@NonNull String displayName, int useSp, int value) {
        this.displayName = displayName;
        this.useSp = useSp;
        this.value = value;
    }

    @NonNull
    public static StatType findByName(@NonNull String name) {
        for(StatType statType : StatType.values()) {
            if(statType.displayName.equals(name)) {
                return statType;
            }
        }

        throw new RuntimeException("statType.unknown");
    }

}
