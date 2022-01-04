package enzoDevoto.apps.medicineTakeCare.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import enzoDevoto.apps.medicineTakeCare.web.exception.ResourceNotFoundException;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientResponse;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void should_Bring_AllPatients_When_NoIdIsPassed() throws Exception {

        mockMvc.perform(get("/api/v1/patients/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void should_ThrowStatusOk_When_userIdGetsThe_Patient() throws Exception {

        PatientDto patientDto = PatientDto.builder().id(1L).build();
        String patientDtoToJson = objectMapper.writeValueAsString(patientDto);
        //given();
  mockMvc.perform(get("/api/v1/patients/"+ 1)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(patientDtoToJson))
            .andExpect(status().isOk());
        then(patientService).should(times(1)).getPatientById(patientDto.getId());

    }
    @Test
    void should_ThrowException_When_Id_IsNot_PassedAsParameter_To_Get_SpecificPatient() throws Exception {
        PatientDto patientDto = PatientDto.builder().build();
        String patientDtoToJson = objectMapper.writeValueAsString(patientDto);
        mockMvc.perform(get("/api/v1/patients/"+ 1)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(patientDtoToJson))
                .andExpect(status().isOk());

            when(this.patientService.getPatientById(patientDto.getId()))
                    .thenThrow(ResourceNotFoundException.class);

            //when
            Executable executable = () -> patientService.getPatientById(patientDto.getId());
            //then
            assertThrows(ResourceNotFoundException.class, executable);

        }

    @Test
    void should_ThrowStatusIsCreated_When_CreateAPatient_Making_PostRequest_ToUrl() throws Exception {
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

        mockMvc.perform(patch("/api/v1/patients/updatePatient/"+1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientDtoToJson))
                .andExpect(status().isNoContent());

    }
}