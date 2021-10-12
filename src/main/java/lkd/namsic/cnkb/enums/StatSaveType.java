package lkd.namsic.cnkb.enums;

import org.springframework.lang.NonNull;

public enum StatSaveType {

    BASIC("statSaveType.basic", 1),
    SP("statSaveType.sp", 2),
    EQUIP("statSaveType.equip", 3),
    BUFF("statSaveType.buff", 4);

    @NonNull
    public final String displayName;

    public final int value;

    StatSaveType(@NonNull String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

}
