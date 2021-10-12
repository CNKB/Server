package lkd.namsic.cnkb.base;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    public static Location toLocation(int hexLocation) {
        short ander = 0xff;
        return Location.builder()
                .x(hexLocation & ander)
                .y(hexLocation >> 8 & ander)
                .fieldX(hexLocation >> 16 & ander)
                .fieldY(hexLocation >> 24 & ander)
                .build();
    }

    public static int fromLocation(@NonNull Location location) {
        int hexLocation = 0x00000000;
        hexLocation |= location.x;
        hexLocation |= location.y << 8;
        hexLocation |= location.fieldX << 16;
        hexLocation |= location.fieldY << 24;

        return hexLocation;
    }

    int x;
    int y;
    int fieldX;
    int fieldY;

    @Override
    public int hashCode() {
        return (x ^ y) ^ (fieldX ^ fieldY);
    }

    public boolean equalsField(@Nullable Object obj) {
        if(this == obj) return true;

        if(obj instanceof Location location) {
            return fieldX == location.fieldX && fieldY == location.fieldY;
        }

        return false;
    }

    public boolean equalsMap(@Nullable Object obj) {
        if(this == obj) return true;

        if(obj instanceof Location location) {
            return x == location.x && y == location.y;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return equalsField(obj) && equalsMap(obj);
    }

}
