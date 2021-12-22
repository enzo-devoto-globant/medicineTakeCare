package enzoDevoto.apps.medicineTakeCare.web.service.impl;

import enzoDevoto.apps.medicineTakeCare.web.model.DoctorDto;
import enzoDevoto.apps.medicineTakeCare.web.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {
    @Override
    public DoctorDto getDoctorById(UUID doctorId) {

        DoctorDto newDoctor = DoctorDto.builder().id(UUID.randomUUID())
                .name("Ruben Donoso")
                .age(50)
                .description("Adummy doctor with years of expereicience and degreews in medical stuff.")
                .email("rubenTuDoctocito@troololo.com")
                .gender("Male")
                .rate(0.10)
                .speciality("Endocrinology")
                .build();
        log.info("Getting a Doctor by UUID: "+ newDoctor.toString());
        return newDoctor;
    }

    @Override
    public DoctorDto setNewDoctorDto(DoctorDto doctorDto) {
        log.info("Creating doctor data: ");
        return DoctorDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateDoctor(UUID doctorDtoId, DoctorDto doctorDto) {

    }

    @Override
    public void deleteDoctor(UUID doctorDtoId) {

    }
}
