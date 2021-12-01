package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnumFinder<T extends Enum<?>> {
    
    private final Map<Integer, T> map = new LinkedHashMap<>();
    
    private EnumFinder() {}
    
    public static <T extends Enum<?>> EnumFinder<T> getFinder(@NonNull T[] values) {
        EnumFinder<T> enumFinder = new EnumFinder<>();
        
        try {
            for(T t : values) {
                enumFinder.map.put(
                    t.getClass().getField("value").getInt(t), t
                );
            }
        } catch(ReflectiveOperationException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
        return enumFinder;
    }
    
    public T find(int value) {
        return map.get(value);
    }
    
}