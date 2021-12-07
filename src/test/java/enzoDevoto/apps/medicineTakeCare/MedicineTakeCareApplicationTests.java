package enzoDevoto.apps.medicineTakeCare;

import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientService;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
class MedicineTakeCareApplicationTests {

	private PatientDto patientDtoMock;
	private PatientService patientService;

	@BeforeEach
	public void setup(){
		this.patientDtoMock = mock(PatientDto.class);
		this.patientService = mock(PatientServiceImpl.class);
	}
	@Test
	void set_User_Name_test() {
		//given
		PatientDto testPatient = PatientDto.builder().name("Enzo")
									.id(UUID.randomUUID())
									.isCritical(true)
									.build();
		//When
		//then
		assertEquals("Enzo", testPatient.getName());
	}

}
