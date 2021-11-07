package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

public interface NamedEnum {
    
    @NonNull
    String getBase();
    
    @NonNull
    default String getName(Enum<?> e) {
        return this.getBase() + "." + e.name().toLowerCase();
    }
    
}