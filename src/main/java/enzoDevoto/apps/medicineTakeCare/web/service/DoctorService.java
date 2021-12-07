package enzoDevoto.apps.medicineTakeCare.web.service;

import enzoDevoto.apps.medicineTakeCare.web.model.DoctorDto;

import java.util.UUID;

public interface DoctorService {
    DoctorDto getDoctorById(UUID doctorId);
}
