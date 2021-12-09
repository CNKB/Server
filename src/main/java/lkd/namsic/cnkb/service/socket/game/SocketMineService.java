package lkd.namsic.cnkb.service.socket.game;

import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.config.GameConfig;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.dto.socket.SocketResponse;
import lkd.namsic.cnkb.enums.Doing;
import lkd.namsic.cnkb.enums.LogType;
import lkd.namsic.cnkb.enums.NamedEnum;
import lkd.namsic.cnkb.enums.VariableType;
import lkd.namsic.cnkb.enums.object.ItemEnum;
import lkd.namsic.cnkb.handler.mnm.PlayerItemHandler;
import lkd.namsic.cnkb.handler.mnm.PlayerVariableHandler;
import lkd.namsic.cnkb.repository.PlayerRepository;
import lkd.namsic.cnkb.service.socket.SocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocketMineService implements SocketService {
    
    private final Config config;
    private final GameConfig gameConfig;
    
    private final PlayerRepository playerRepository;
    private final PlayerVariableHandler playerVariableHandler;
    private final PlayerItemHandler playerItemHandler;
    
    @NonNull
    @Override
    public String getRequest() {
        return "mine";
    }
    
    @NonNull
    @Override
    public Map<String, Class<?>> getRequiredArgs() {
        return new HashMap<>() {{
            put("mineLv", Integer.class);
        }};
    }
    
    @Override
    public boolean invalidOnDoing() {
        return true;
    }
    
    @NonNull
    @Override
    public SocketResponse handleData(@NonNull Player player, @NonNull WebSocketSession session,
                                     @NonNull Map<String, Object> inputData) {
        player.setDoing(Doing.MINE.value);
        playerRepository.save(player);
        
        config.sleep(config.getRandom(500, 500));
        
        int mineLv = (int) inputData.get("mineLv");
        int maxMineLv = playerVariableHandler.getVariable(player, VariableType.MINE_LV, 1);
    
        if(mineLv > maxMineLv) {
            return SocketResponse.builder()
                .status(400)
                .message("현재 최대 광질 레벨은 " + maxMineLv + " 입니다")
                .build();
        }
        
        List<Double> minePercentList = gameConfig.MINE_PERCENT.get(mineLv);
        double randomValue = Math.random();
        double stackValue = 0;
        
        Map<Long, Integer> tempData = new HashMap<>();
        double minePercent;
        int size = minePercentList.size();
        for(int index = 0; index < size; index++) {
            minePercent = minePercentList.get(index);
            
            if(stackValue + minePercent > randomValue) {
                tempData.put(gameConfig.MINE_ITEM_ID.get(index), 1);
                break;
            } else {
                stackValue += minePercent;
            }
        }
        
        //Enhancement: Event Handling
        
        Map<String, Object> data = new HashMap<>();
        Map<String, Integer> minedMap = new HashMap<>();
        Map<String, Integer> beforeMap = new HashMap<>();
    
        String itemName;
        long key;
        int value, before;
        for(Map.Entry<Long, Integer> entry : tempData.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            
            itemName = NamedEnum.getName(ItemEnum.finder.find(key));
            before = playerItemHandler.addCount(player, key, value);
            
            minedMap.put(itemName, value);
            beforeMap.put(itemName, before);
        }
        
        data.put("mined", minedMap);
        data.put("before", beforeMap);
        config.log(LogType.MINE, player, data.toString());
        
        player.setDoing(Doing.NONE.value);
        playerRepository.save(player);
        
        return SocketResponse.builder()
            .data(data)
            .build();
    }
    
}
