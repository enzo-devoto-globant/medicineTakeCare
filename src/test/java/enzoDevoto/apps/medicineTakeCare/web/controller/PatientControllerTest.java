package enzoDevoto.apps.medicineTakeCare.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @MockBean
    PatientService patientService;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;



    @Test
    void getPatient() throws Exception {

        mockMvc.perform(get("/api/v1/patients/"+ UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void postPatient() throws Exception {
        //given
        PatientDto patientDto = PatientDto.builder().build();
        String patientDtoToJson = objectMapper.writeValueAsString(patientDto);

        mockMvc.perform(post("/api/v1/patients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientDtoToJson))
                        .andExpect(status().isCreated());

    }

    @Test
    void updatePatient() throws Exception {
        PatientDto patientDto = PatientDto.builder().build();
        String patientDtoToJson = objectMapper.writeValueAsString(patientDto);

        mockMvc.perform(put("/api/v1/patients/updatePatient/"+UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientDtoToJson))
                .andExpect(status().isNoContent());

    }
}