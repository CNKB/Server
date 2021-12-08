package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnumFinder<K extends Number, T extends ValuedEnum<K>> {
    
    private final Map<K, T> map = new LinkedHashMap<>();
    
    private EnumFinder() {}
    
    public static <K extends Number, T extends ValuedEnum<K>> EnumFinder<K, T> getFinder(@NonNull T[] values) {
        EnumFinder<K, T> enumFinder = new EnumFinder<>();
        for(T t : values) {
            enumFinder.map.put(t.getValue(), t);
        }
    
        return enumFinder;
    }
    
    public T find(K value) {
        return map.get(value);
    }
    
}