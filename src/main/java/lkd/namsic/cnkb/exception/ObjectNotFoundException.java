package lkd.namsic.cnkb.exception;

import lkd.namsic.cnkb.config.Config;
import lkd.namsic.cnkb.domain.game.player.Player;
import lkd.namsic.cnkb.enums.LogType;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Getter
public class ObjectNotFoundException extends CommonException {
    
    Class<?> c;
    Object key;
    
    public ObjectNotFoundException(@Nullable Player player, @NonNull Class<?> c, @NonNull Object key) {
        this.c = c;
        this.key = key;
        
        Config.getInstance().log(
            LogType.OBJECT_NOT_FOUND, player,
            c.getName() + " - " + key
        );
    }
    
}
