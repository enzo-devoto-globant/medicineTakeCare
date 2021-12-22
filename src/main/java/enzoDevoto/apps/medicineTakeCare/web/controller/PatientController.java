package enzoDevoto.apps.medicineTakeCare.web.controller;

import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/patients")
@RestController
@Slf4j
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping("/persons")
    public List<ResponseEntity<PatientDto>> getPatients(@PathVariable("patientId") UUID patientId){
        log.info("Getting all patients: ");
        List<ResponseEntity<PatientDto>> patients = new ArrayList<>();
        patients.add(new ResponseEntity<>(PatientDto.builder().build() ,HttpStatus.OK));
        return patients;
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("patientId") UUID patientId){
        log.info("Getting a patient by UUID: ");
        return new ResponseEntity<>(patientService.getPatientById(patientId), HttpStatus.OK);
    }
    @PostMapping
    @ResponseStatus
    public ResponseEntity saveNewPatient(@Valid @RequestBody PatientDto patientDto){

        log.info("Creating new Patient: " + patientDto);
        return new ResponseEntity<>(patientService.saveNewPatient(patientDto), HttpStatus.CREATED);

    }

    @PutMapping({"/updatePatient/{patientId}"})
    public ResponseEntity updatePatient(@PathVariable("patientId")UUID patientId, @RequestBody PatientDto patientDto){
        log.info("Updating a patient by UUID: " + patientId + " : " + patientDto);
        patientService.updatePatient(patientId, patientDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"deletePatient/{patientId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable("patientId")UUID patientId){
        log.info("Deleting a patient by UUID: ");
        patientService.deletePatient(patientId);
    }
}
