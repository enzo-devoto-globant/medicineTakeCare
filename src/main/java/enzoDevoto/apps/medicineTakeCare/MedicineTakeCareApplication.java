package enzoDevoto.apps.medicineTakeCare;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MedicineTakeCareApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	};
	public static void main(String[] args) {
		SpringApplication.run(MedicineTakeCareApplication.class, args);
	}
}
