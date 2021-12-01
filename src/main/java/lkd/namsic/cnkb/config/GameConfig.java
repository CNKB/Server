package lkd.namsic.cnkb.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record GameConfig() {
    
    public static final List<Long> MINE_ITEM_ID = Arrays.asList(
    
    );
    public static final Map<Integer, Double[]> MINE_PERCENT = new HashMap<Integer, Double[]>() {{
        put(1, new Double[]{
            50D, 35D, 15D
        });
    }};

}