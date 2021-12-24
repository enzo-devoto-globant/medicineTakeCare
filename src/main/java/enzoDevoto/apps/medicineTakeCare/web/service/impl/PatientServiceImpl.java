package enzoDevoto.apps.medicineTakeCare.web.service.impl;

import enzoDevoto.apps.medicineTakeCare.web.entity.Patient;
import enzoDevoto.apps.medicineTakeCare.web.exception.ResourceNotFoundException;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.repository.PatientRepository;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientDto> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient -> mapToDto(patient)).collect(Collectors.toList());
    }

    @Override
    public PatientDto getPatientById(Long patientId) {
        log.info("getting patient data: ");
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("post", "id", patientId));
        return mapToDto(patient);
    }


    @Override
    public PatientDto updatePatient(Long patientId, PatientDto patientDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("post", "id", patientId));
        patient.setAddress(patient.getAddress());
        patient.setId(patientDto.getId());
        patient.setGender(patientDto.getGender());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setEmail(patientDto.getEmail());
        patient.setDescription(patientDto.getDescription());
        patient.setCritical(patientDto.isCritical());
        patient.setAge(patientDto.getAge());
        patient.setName(patientDto.getName());
        patient.setTimeOfEvaluation(patientDto.getTimeOfEvaluation());
        Patient updatedPatient = patientRepository.save(patient);
        return mapToDto(updatedPatient);
    }

    @Override
    public void deletePatient(Long patientId) {
        log.debug("Deleting a patient");
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", patientId));
        patientRepository.delete(patient);
    }

    @Override
    public PatientDto saveNewPatient(PatientDto patientDto) {
        log.info("Creating patient data: ");
        Patient patientRequest = mapToEntity(patientDto);
        Patient newPatient = patientRepository.save(patientRequest);
        //convert entity to dto
        PatientDto patientResponse = mapToDto(newPatient);
        return patientResponse;
    }

    private PatientDto mapToDto(Patient patient) {
        PatientDto patientResponse = new PatientDto();
        patientResponse.setName(patient.getName());
        patientResponse.setCritical(patient.isCritical());
        patientResponse.setAge(patient.getAge());
        patientResponse.setEmail(patient.getEmail());
        patientResponse.setTimeOfEvaluation(patient.getTimeOfEvaluation());
        patientResponse.setDescription(patient.getDescription());
        patientResponse.setGender(patient.getGender());
        patientResponse.setPhoneNumber(patient.getPhoneNumber());
        patientResponse.setId(patient.getId());
        patientResponse.setAddress(patient.getAddress());
        return patientResponse;
    }

    private Patient mapToEntity(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        patient.setAge(patientDto.getAge());
        patient.setCritical(patientDto.isCritical());
        patient.setDescription(patientDto.getDescription());
        patient.setEmail(patientDto.getEmail());
        patient.setGender(patientDto.getGender());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setTimeOfEvaluation(patientDto.getTimeOfEvaluation());
        patient.setAddress(patientDto.getAddress());

        return patient;
    }
}
