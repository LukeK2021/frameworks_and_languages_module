package luke.lukes.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LukesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LukesServerApplication.class, args);
		System.getProperties().put( "server.port", 8000 );
	}

}
