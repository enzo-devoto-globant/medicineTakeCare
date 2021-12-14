package enzoDevoto.apps.medicineTakeCare.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import enzoDevoto.apps.medicineTakeCare.web.model.DoctorDto;
import enzoDevoto.apps.medicineTakeCare.web.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {

    @MockBean
    DoctorService doctorService;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getDoctor() throws Exception {
        mockMvc.perform(get("/api/v1/doctors/"+ UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void postDoctor() throws Exception {
        DoctorDto doctorDto = DoctorDto.builder().build();
        String patientDtoToJson = objectMapper.writeValueAsString(doctorDto);

        mockMvc.perform(post("/api/v1/doctors/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientDtoToJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateDoctor() throws Exception {
        DoctorDto doctorDto = DoctorDto.builder().build();
        String patientDtoToJson = objectMapper.writeValueAsString(doctorDto);

        mockMvc.perform(put("/api/v1/doctors/updateDoctor/"+UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientDtoToJson))
                .andExpect(status().isNoContent());
    }

}