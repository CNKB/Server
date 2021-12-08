package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

public enum StatSaveType implements NamedEnum, ValuedEnum<Integer> {
    
    BASIC(1),
    SP(2),
    EQUIP(3),
    BUFF(4);
    
    public static final EnumFinder<Integer, StatSaveType> finder = EnumFinder.getFinder(StatSaveType.values());
    public final int value;
    
    StatSaveType(int value) {
        this.value = value;
    }
    
    @NonNull
    @Override
    public String getBase() {
        return "statSaveType";
    }
    
    @Override
    public Integer getValue() { return this.value; }
    
}