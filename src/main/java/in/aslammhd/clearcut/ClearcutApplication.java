package in.aslammhd.clearcut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClearcutApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClearcutApplication.class, args);
	}

}
