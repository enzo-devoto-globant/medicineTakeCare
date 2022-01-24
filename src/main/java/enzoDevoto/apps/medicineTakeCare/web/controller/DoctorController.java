package enzoDevoto.apps.medicineTakeCare.web.controller;

import enzoDevoto.apps.medicineTakeCare.web.model.DoctorDto;
import enzoDevoto.apps.medicineTakeCare.web.model.DoctorResponse;
import enzoDevoto.apps.medicineTakeCare.web.service.DoctorService;
import enzoDevoto.apps.medicineTakeCare.web.utils.PatientConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
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

@Api(value = "CRUD REST API fot Doctors resources")
@RequestMapping("/api/v1/doctors")
@RestController
@Slf4j
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @ApiOperation(value = "Get all Doctors users from Doctor REST API")
    @GetMapping()
    public DoctorResponse getDoctorsV1(
            @RequestParam(value = "pageNo", defaultValue = PatientConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PatientConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PatientConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PatientConstants.DEFAULT_SORT_DIRECCION, required = false) String sortDir
    ){
        return doctorService.getDoctors(pageNumber,pageSize, sortBy, sortDir);
    }
    @ApiOperation(value = "Get Doctor user by id from Doctor REST API")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping({"/{doctorId}"})
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse getDoctorByIdV1(@PathVariable("doctorId") Long doctorId){
        log.info("Getting a doctor by ID: ");
        return doctorService.getDoctorById(doctorId);
    }
    @ApiOperation(value = "Create a Doctor user for Doctor REST API")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DoctorDto> saveNewDoctorV1(@Valid @RequestBody  DoctorDto doctorDto){
        log.info("Creating new doctor: " + doctorDto);
        return new ResponseEntity<>(doctorService.setNewDoctorDto(doctorDto), HttpStatus.CREATED);

    }
    @ApiOperation(value = "Patch a Doctor user by id from Doctor REST API")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PatchMapping({"/updateDoctor/{doctorId}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity updateDoctorV1(@PathVariable("doctorId")Long doctorId, @Valid @RequestBody DoctorDto doctorDto){
        log.info("Updating a doctor by id: " + doctorId + " : " + doctorDto);
        doctorService.updateDoctor(doctorId, doctorDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @ApiOperation(value = "Delete a Doctor user by Id from Doctor REST API")
    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping({"/deleteDoctor/{doctorId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctorV1(@PathVariable("doctorId")Long doctorId){
        log.info("Deleting a doctor by ID: ");
        doctorService.deleteDoctor(doctorId);
    }
}
