package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

public enum VariableType implements NamedEnum, ValuedEnum<Integer> {

    MINE_LV(1);

    public static final EnumFinder<Integer, VariableType> finder = EnumFinder.getFinder(VariableType.values());
    public final int value;

    VariableType(int value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String getBase() {
        return "variable";
    }
    
    @Override
    public Integer getValue() { return this.value; }

}