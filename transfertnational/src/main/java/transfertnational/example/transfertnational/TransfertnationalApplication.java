package transfertnational.example.transfertnational;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("transfertnational.example.transfertnational")
public class TransfertnationalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransfertnationalApplication.class, args);
	}

}
