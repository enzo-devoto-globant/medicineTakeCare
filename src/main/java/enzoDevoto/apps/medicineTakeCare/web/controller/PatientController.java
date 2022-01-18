package enzoDevoto.apps.medicineTakeCare.web.controller;

import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientResponse;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientService;
import enzoDevoto.apps.medicineTakeCare.web.utils.PatientConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


@RequestMapping("/api/v1/patients")
@RestController
@Slf4j
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public PatientResponse getPatients(
            @RequestParam(value = "pageNo", defaultValue = PatientConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PatientConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PatientConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PatientConstants.DEFAULT_SORT_DIRECCION, required = false) String sortDir
    ){
        return patientService.getPatients(pageNumber,pageSize, sortBy, sortDir);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping({"/{patientId}"})
    @ResponseStatus(HttpStatus.OK)
    public PatientResponse getPatientById (@PathVariable("patientId") Long patientId){
        log.info("Getting a patient by UUID: ");
        return patientService.getPatientById(patientId);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PatientDto> saveNewPatient(@Valid @RequestBody  PatientDto patientDto){
        log.info("Creating new Patient: " + patientDto);
        return new ResponseEntity<>(patientService.saveNewPatient(patientDto), HttpStatus.CREATED);

    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PatchMapping({"/updatePatient/{patientId}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity updatePatient(@PathVariable("patientId")Long patientId,@Valid @RequestBody  PatientDto patientDto){
        log.info("Updating a patient by id: " + patientId + " : " + patientDto);
        patientService.updatePatient(patientId, patientDto);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping({"/deletePatient/{patientId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable("patientId")Long patientId){
        log.info("Deleting a patient by UUID: ");
        patientService.deletePatient(patientId);
    }

}
