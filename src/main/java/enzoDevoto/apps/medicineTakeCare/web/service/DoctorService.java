package enzoDevoto.apps.medicineTakeCare.web.service;

import enzoDevoto.apps.medicineTakeCare.web.model.DoctorDto;
import enzoDevoto.apps.medicineTakeCare.web.model.DoctorResponse;
import org.springframework.http.ResponseEntity;

public interface DoctorService {
    DoctorResponse getDoctorById(Long doctorId);

    DoctorDto setNewDoctorDto(DoctorDto doctorDto);

    DoctorDto updateDoctor(Long doctorDtoId, DoctorDto doctorDto);

    void deleteDoctor(Long doctorDtoId);

    DoctorResponse getDoctors(int pageNumber, int pageSize, String sortBy, String sortDir);
}
