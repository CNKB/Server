package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;

public enum Doing {

    NONE("doing.none", 0),
    ADVENTURE("doing.adventure", 1),
    APPRAISE("doing.appraise", 2),
    CHAT("doing.chat", 3),
    FIGHT("doing.fight" ,4),
    FIGHT_ONE("doing.fightOne", 5),
    FISH("doing.fish", 6),
    MINE("doing.mine", 7),
    REINFORCE("doing.reinforce", 8),
    REST("doing.rest", 9),
    SHOP("doing.shop", 10),
    WAIT_RESPONSE("doing.waitResponse", 11);

    public final static List<Doing> fightableList = Arrays.asList(
            Doing.NONE,
            Doing.ADVENTURE,
            Doing.FIGHT
    );

    @NonNull
    public final String displayName;

    public final int value;

    Doing(@NonNull String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

}
