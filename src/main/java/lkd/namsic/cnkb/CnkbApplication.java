package lkd.namsic.cnkb;

import lkd.namsic.cnkb.config.init.GameInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@SpringBootApplication
public class CnkbApplication {
    
    private final GameInitializer initializer;
    
    public static void main(String[] args) {
        SpringApplication.run(CnkbApplication.class, args);
    }
    
    @PostConstruct
    public void initDb() {
        initializer.initDb();
    }
    
}