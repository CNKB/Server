package lkd.namsic.cnkb;

import lkd.namsic.cnkb.config.init.GameInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CnkbApplication {

	@Autowired
	GameInitializer initializer;

	public static void main(String[] args) {
		SpringApplication.run(CnkbApplication.class, args);
	}

	@PostConstruct
	public void initDb() {
		initializer.initDb();
	}

}
