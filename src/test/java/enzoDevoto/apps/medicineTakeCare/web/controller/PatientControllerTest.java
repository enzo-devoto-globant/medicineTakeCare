package enzoDevoto.apps.medicineTakeCare.web.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getPatient() throws Exception {
        PatientDto patient = PatientDto.builder().build();
        String patientDtoToJson = objectMapper.writeValueAsString(patient);

        mockMvc.perform(get("/api/v1/patients/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientDtoToJson))
                            .andExpect(status().isOk());

    }

    @Test
    void postPatient() throws Exception {
        PatientDto patient = PatientDto.builder().build();
        String patientDtoToJson = objectMapper.writeValueAsString(patient);

        mockMvc.perform(post("/api/v1/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(patientDtoToJson))
                .andExpect(status().isCreated());

    }

    @Test
    void updatePatient() throws Exception {
        PatientDto patient = PatientDto.builder().build();
        String patientDtoToJson = objectMapper.writeValueAsString(patient);

        mockMvc.perform(put("/api/v1/patients/"+UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(patientDtoToJson))
                .andExpect(status().isNoContent());
    }

}