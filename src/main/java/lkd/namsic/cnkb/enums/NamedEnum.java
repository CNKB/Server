package lkd.namsic.cnkb.enums;

import com.google.common.base.CaseFormat;
import org.springframework.lang.NonNull;

public interface NamedEnum {

    @NonNull
    static <T extends Enum<?> & NamedEnum> String getName(T t) {
        return t.getBase() + "." + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, t.name().toLowerCase());
    }

    @NonNull
    String getBase();

}