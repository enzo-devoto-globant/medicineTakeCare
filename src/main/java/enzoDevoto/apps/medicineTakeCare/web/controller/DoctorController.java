package enzoDevoto.apps.medicineTakeCare.web.controller;

import enzoDevoto.apps.medicineTakeCare.web.model.DoctorDto;
import enzoDevoto.apps.medicineTakeCare.web.service.DoctorService;
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

import java.util.UUID;

@RequestMapping("/api/v1/doctors")
@RestController
@Slf4j
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping({"/{doctorId}"})
    public ResponseEntity  getDoctor(@PathVariable("doctorId")UUID doctorId){
       log.info("Getting a Doctor by UUID: " +doctorService.getDoctorById(doctorId));
        return new ResponseEntity<>(doctorService.getDoctorById(doctorId), HttpStatus.OK);
    }

    @PostMapping    
    public ResponseEntity postDoctor(@RequestBody DoctorDto doctorDto){
        DoctorDto newdoctorDto = doctorService.setNewDoctorDto(doctorDto);
        log.info("Creating a Doctor: ");
        return new ResponseEntity(HttpStatus.CREATED);

    };


    @PutMapping({"/updateDoctor/{doctorDto}"})
    public ResponseEntity updateDoctor(@PathVariable("doctorDto")UUID doctorDtoId, @RequestBody DoctorDto doctorDto){
        log.info("Updating a doctor by UUID: ");
        doctorService.updateDoctor(doctorDtoId, doctorDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/deleteDoctor/{doctorDto}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable("doctorDto")UUID doctorDtoId){
        log.info("Deleting a doctor by UUID: ");
        doctorService.deleteDoctor(doctorDtoId);
    }
}
