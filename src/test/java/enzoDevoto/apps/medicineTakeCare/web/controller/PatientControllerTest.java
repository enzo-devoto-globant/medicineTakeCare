package enzoDevoto.apps.medicineTakeCare.web.controller;


import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


class PatientControllerTest {

    private static String  API_URL =  "/api/v1/patient/";
    private PatientController patientController;
    private PatientDto patientDto;
    private PatientService patientService;

    @BeforeEach
    void setup(){

        this.patientController = mock(PatientController.class);
        this.patientService = mock(PatientService.class);
        this.patientDto = mock(PatientDto.class);


    }

    @Test
    void getPatient() throws Exception {
        //given

        Map<String, PatientDto> patientServiceMock = patientService.getPatientById(patientDto.getId());
        PatientDto myNewPatient = PatientDto.builder().id(UUID.randomUUID()).age(30)
                .name("Enzo")
                .email("enzocorreo@tusca.clclc")
                .isCritical(true)
                .description("Some description")
                .timeOfEvaluation(Date.from(Instant.now()))
                .build();
        patientServiceMock.put(myNewPatient.getId().toString(),myNewPatient);

        //when

        Map<String, PatientDto> patient = patientService.getPatientById(UUID.fromString("490adb47-5bd6-4f49-a3a0-c77485e445f3"));
        assertEquals("Enzo", myNewPatient.getName());

    }


    @Test
    void postPatient() throws Exception {

    }

    @Test
    void updatePatient() throws Exception {

    }

}