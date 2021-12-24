package enzoDevoto.apps.medicineTakeCare.web.controller;

import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientResponse;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/patients")
@RestController
@Slf4j
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping()
    public PatientResponse getPatients(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return patientService.getPatients(pageNumber,pageSize);
    }

    @GetMapping({"/{patientId}"})
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("patientId") Long patientId){
        log.info("Getting a patient by UUID: ");
        return new ResponseEntity<PatientDto>((MultiValueMap<String, String>) patientService.getPatientById(patientId), HttpStatus.OK);
    }
    @PostMapping
    @ResponseStatus
    public ResponseEntity<PatientDto> saveNewPatient(@Valid @RequestBody PatientDto patientDto){
        log.info("Creating new Patient: " + patientDto);
        return new ResponseEntity<>(patientService.saveNewPatient(patientDto), HttpStatus.CREATED);

    }

    @PatchMapping({"/updatePatient/{patientId}"})
    public ResponseEntity updatePatient(@PathVariable("patientId")Long patientId, @RequestBody PatientDto patientDto){
        log.info("Updating a patient by id: " + patientId + " : " + patientDto);
        patientService.updatePatient(patientId, patientDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/deletePatient/{patientId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable("patientId")Long patientId){
        log.info("Deleting a patient by UUID: ");
        patientService.deletePatient(patientId);
    }
}
