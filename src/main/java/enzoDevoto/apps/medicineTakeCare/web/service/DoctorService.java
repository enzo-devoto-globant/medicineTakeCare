package enzoDevoto.apps.medicineTakeCare.web.service;

import enzoDevoto.apps.medicineTakeCare.web.model.DoctorDto;

import java.util.UUID;

public interface DoctorService {
    DoctorDto getDoctorById(UUID doctorId);

    DoctorDto setNewDoctorDto(DoctorDto doctorDto);

    void updateDoctor(UUID doctorDtoId, DoctorDto doctorDto);

    void deleteDoctor(UUID doctorDtoId);
}
