package lkd.namsic.cnkb.dto.socket;

import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

public class SocketInnerData {

    @ToString
    @Getter
    public static class Button {
        public Button(@NonNull String buttonName) {
            this.buttonName = buttonName;
        }

        private final String buttonName;
    }

    @ToString
    @Getter
    public static class UpDownButton extends Button {
        public UpDownButton(@NonNull String buttonName, int min, int max) {
            super(buttonName);
            this.min = min;
            this.max = max;
        }

        private final int min, max;
    }

}
