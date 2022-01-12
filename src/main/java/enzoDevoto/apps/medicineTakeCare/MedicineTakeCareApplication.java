package enzoDevoto.apps.medicineTakeCare;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class MedicineTakeCareApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	};

	public static void main(String[] args) {
		SpringApplication.run(MedicineTakeCareApplication.class, args);
	}
//This is a comment line to test the Github a local repository sync.

}
