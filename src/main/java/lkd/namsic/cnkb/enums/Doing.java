package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

public enum Doing implements NamedEnum, ValuedEnum<Integer> {
    
    NONE(0),
    ADVENTURE(1),
    APPRAISE(2),
    CHAT(3),
    FIGHT(4),
    FIGHT_ONE(5),
    FISH(6),
    MINE(7),
    REINFORCE(8),
    REST(9),
    SHOP(10),
    WAIT_RESPONSE(11);
    
    public static final EnumFinder<Integer, Doing> finder = EnumFinder.getFinder(Doing.values());
    public final int value;
    
    Doing(int value) {
        this.value = value;
    }
    
    @NonNull
    @Override
    public String getBase() {
        return "doing";
    }
    
    @Override
    public Integer getValue() { return this.value; }
    
}