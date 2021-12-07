package enzoDevoto.apps.medicineTakeCare.web.controller;

import enzoDevoto.apps.medicineTakeCare.web.model.DoctorDto;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable("doctorId")UUID doctorId){
       log.info("Getting a Doctor by UUID: " +doctorService.getDoctorById(doctorId).getName());
        return new ResponseEntity<>(doctorService.getDoctorById(doctorId), HttpStatus.OK);
    }
}
