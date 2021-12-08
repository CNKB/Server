package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

public enum StatType implements NamedEnum, ValuedEnum<Integer> {
    
    MAXHP(1, 1),
    HP(2, 0),
    MAXMN(3, 3),
    MN(4, 0),
    ATK(5, 5),
    MATK(6, 5),
    AGI(7, 1),
    ATS(8, 1),
    DEF(9, 3),
    MDEF(10, 30),
    BRE(11, 21),
    MBRE(12, 22),
    DRA(13, 53),
    MDRA(14, 54),
    EVA(15, 15),
    ACC(16, 16);
    
    public static final EnumFinder<Integer, StatType> finder = EnumFinder.getFinder(StatType.values());
    public final int value;
    public final int useSp;
    
    StatType(int value, int useSp) {
        this.value = value;
        this.useSp = useSp;
    }
    
    @NonNull
    @Override
    public String getBase() {
        return "statType";
    }
    
    @Override
    public Integer getValue() { return this.value; }
    
}