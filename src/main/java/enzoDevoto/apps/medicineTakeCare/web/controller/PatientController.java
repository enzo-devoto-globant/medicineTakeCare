package enzoDevoto.apps.medicineTakeCare.web.controller;

import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/v1/patients")
@RestController
@Slf4j
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping({"/{patientId}"})
    public Map<String,PatientDto> getPatient(@PathVariable("patientId") UUID patientId){
        log.info("Getting a patient by UUID: ");
        Map<String, PatientDto> newList = patientService.getPatientById(patientId);
            return newList;
    };
    @PostMapping
    public ResponseEntity<PatientDto> postPatient(PatientDto patientDto){
        PatientDto newPatientDto = patientService.setNewDto(patientDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/patients"+newPatientDto.getId().toString());
        log.info("Creating a patient: ");
        return new ResponseEntity<>(headers,HttpStatus.OK);
    };
    @PutMapping({"/{patientId}"})
    public ResponseEntity<PatientDto> updatePatient(@PathVariable("patientId")UUID patientId, PatientDto patientDto){
        log.info("Updating a patient by UUID: ");
        patientService.updatePatient(patientId, patientDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{patientId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable("patientId")UUID patientId){
        log.info("Deleting a patient by UUID: ");
        patientService.deletePatient(patientId);
    }
}
