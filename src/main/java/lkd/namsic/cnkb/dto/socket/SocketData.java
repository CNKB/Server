package lkd.namsic.cnkb.dto.socket;

import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;

public class SocketData {
    
    @ToString
    @Getter
    public static class Button {
        private final String buttonName;
        
        public Button(@NonNull String buttonName) {
            this.buttonName = buttonName;
        }
       
        @NonNull
        public static String getName() {
            return "button";
        }
    }
    
    @ToString
    @Getter
    public static class UpDownButton extends Button {
        private final int min, max;
        
        public UpDownButton(@NonNull String buttonName, int min, int max) {
            super(buttonName);
            this.min = min;
            this.max = max;
        }
    
        @NonNull
        public static String getName() {
            return "upDownButton";
        }
    }
    
}