package enzoDevoto.apps.medicineTakeCare.web.controller;

import enzoDevoto.apps.medicineTakeCare.web.model.DoctorDto;
import enzoDevoto.apps.medicineTakeCare.web.model.DoctorResponse;
import enzoDevoto.apps.medicineTakeCare.web.service.DoctorService;
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

@RequestMapping("/api/v1/doctors")
@RestController
@Slf4j
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    public DoctorResponse getDoctors(
            @RequestParam(value = "pageNo", defaultValue = PatientConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PatientConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PatientConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PatientConstants.DEFAULT_SORT_DIRECCION, required = false) String sortDir
    ){
        return doctorService.getDoctors(pageNumber,pageSize, sortBy, sortDir);
    }

    @GetMapping({"/{doctorId}"})
    public DoctorResponse getDoctorById(@PathVariable("doctorId") Long doctorId){
        log.info("Getting a doctor by ID: ");
        return doctorService.getDoctorById(doctorId);
    }
    @PostMapping
    @ResponseStatus
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorDto> saveNewDoctor(@Valid @RequestBody DoctorDto doctorDto){
        log.info("Creating new doctor: " + doctorDto);
        return new ResponseEntity<>(doctorService.setNewDoctorDto(doctorDto), HttpStatus.CREATED);

    }

    @PatchMapping({"/updateDoctor/{doctorId}"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateDoctor(@PathVariable("doctorId")Long doctorId,@Valid  @RequestBody DoctorDto doctorDto){
        log.info("Updating a doctor by id: " + doctorId + " : " + doctorDto);
        doctorService.updateDoctor(doctorId, doctorDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/deleteDoctor/{doctorId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDoctor(@PathVariable("doctorId")Long doctorId){
        log.info("Deleting a doctor by ID: ");
        doctorService.deleteDoctor(doctorId);
    }
}
