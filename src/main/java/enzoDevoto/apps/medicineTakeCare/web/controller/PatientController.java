package enzoDevoto.apps.medicineTakeCare.web.controller;

import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientService;
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

import java.util.UUID;

@RequestMapping("/api/v1/patients")
@RestController

public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping({"/{patientId}"})
    public ResponseEntity<PatientDto> getPatient(@PathVariable("patientId") UUID patientId){
            return new ResponseEntity<>(patientService.getPatientById(patientId), HttpStatus.OK);
    };
    @PostMapping
    public ResponseEntity<PatientDto> postPatient(PatientDto patientDto){
        PatientDto newPatientDto = patientService.setNewDto(patientDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/patients"+newPatientDto.getId().toString());

        return new ResponseEntity<>(headers,HttpStatus.OK);
    };
    @PutMapping({"/{patientId}"})
    public ResponseEntity<PatientDto> updatePatient(@PathVariable("patientId")UUID patientId, PatientDto patientDto){
        patientService.updatePatient(patientId, patientDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{patientId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable("patientId")UUID patientId){
        patientService.deletePatient(patientId);
    }
}
