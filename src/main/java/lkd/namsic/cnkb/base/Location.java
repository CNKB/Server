package lkd.namsic.cnkb.base;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Getter
@Setter
@Builder
public class Location {

    int x;
    int y;
    int fieldX;
    int fieldY;

    @NonNull
    public static Location toLocation(int hex) {
        short ander = 0xff;
        return Location.builder()
            .x(hex >> 24)
            .y(hex >> 16 & ander)
            .fieldX(hex >> 24 & ander)
            .fieldY(hex & ander)
            .build();
    }
    
    public static int toHex(int x, int y) {
        return toHex(x, y, 0, 0);
    }
    
    public static int toHex(int x, int y, int fieldX, int fieldY) {
        int hexLocation = 0x00000000;
        hexLocation |= x << 24;
        hexLocation |= y << 16;
        hexLocation |= fieldX << 8;
        hexLocation |= fieldY;
    
        return hexLocation;
    }

    public int toHex() {
        return toHex(this.x, this.y, this.fieldX, this.fieldY);
    }

    @Override
    public int hashCode() {
        return ((x ^ y) << 16) | ((fieldX ^ fieldY) >> 16);
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
        return this.equalsField(obj) && this.equalsMap(obj);
    }

    @NonNull
    public String toMapString() {
        return x + "-" + y;
    }

    @NonNull
    public String toFieldString() {
        return "(" + fieldX + "-" + fieldY + ")";
    }

    @Override
    @NonNull
    public String toString() {
        return this.toMapString() + " - " + this.toFieldString();
    }

}