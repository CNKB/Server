package lkd.namsic.cnkb.enums.object;

import lkd.namsic.cnkb.enums.EnumFinder;
import lkd.namsic.cnkb.enums.NamedEnum;
import lkd.namsic.cnkb.enums.ValuedEnum;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public enum ItemEnum implements NamedEnum, ValuedEnum<Long> {
    
    STONE(1, "가장 기본적인 광석이다. 감정에 주로 사용된다"),
    COAL(2, "불에 잘 타는 광물이다"),
    QUARTZ(3, "마력 보관이 용이한 광물이다"),
    COPPER(4, "혼자 쓰이기는 어려우나, 합금으로써는 유용한 광물이다"),
    TIN(5, "흔하지만 마력 전달력이 높은 광물이다"),
    IRON(6, "매우 안정적인 금속이다. 물리적인 성질은 뛰어나나, 마력 전달력은 전무하다"),
    BLUE_STONE(7, "음의 기운을 가지는 대표적인 광물이다"),
    RED_STONE(8, "양의 기운을 가지는 대표적인 광물이다"),
    SILVER(9, "근본적으로 회복을 부정하는 성질을 지닌 금속이다"),
    HARD_COAL(10, "기존 석탄보다 훨씬 좋은 품질을 지닌 광물이다"),
    TITANIUM(11, "철보다도 물리적인 성질이 뛰어난 금속이다. 마력 전달력 또한 소량 지니고 있다"),
    BLACK_QUARTZ(12, "음의 기운이 매우 강하게 깃든 석영이다"),
    WHITE_QUARTZ(13, "양의 기운이 매우 강하게 깃든 석영이다"),
    ORIHARCON(14, "가공 방법에 따라 물리적/마법적 성질이 쉽게 바뀌는 특이한 금속이다"),
    YIN_YANG_STONE(15, "음과 양의 기운을 모두 담을 수 있는 희귀한 광석이다"),
    AITUME(16, "알 수 없는 능력이 있다고 알려진 희귀한 광석이다"),
    LANDIUM(17, "알 수 없는 능력이 있다고 알려진 희귀한 광석이다");
    
    public static final EnumFinder<Long, ItemEnum> finder = EnumFinder.getFinder(ItemEnum.values());
    
    public final long value;
    public final String des;
    public final String gainDes;
    public final String useDes;
    public final String eatDes;
    
    ItemEnum(int value, @NonNull String des) {
        this(value, des, null, null, null);
    }
    
    ItemEnum(int value, @NonNull String des, @Nullable String gainDes,
             @Nullable String useDes, @Nullable String eatDes) {
        this.value = value;
        this.des = des;
        this.gainDes = gainDes;
        this.useDes = useDes;
        this.eatDes = eatDes;
    }
    
    @NonNull
    @Override
    public String getBase() {
        return "item";
    }
    
    @Override
    public Long getValue() { return this.value; }

}
