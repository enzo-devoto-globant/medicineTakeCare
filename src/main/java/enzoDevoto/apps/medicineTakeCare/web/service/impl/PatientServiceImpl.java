package enzoDevoto.apps.medicineTakeCare.web.service.impl;

import enzoDevoto.apps.medicineTakeCare.web.entity.Patient;
import enzoDevoto.apps.medicineTakeCare.web.exception.ResourceNotFoundException;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientResponse;
import enzoDevoto.apps.medicineTakeCare.web.repository.PatientRepository;
import enzoDevoto.apps.medicineTakeCare.web.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class PatientServiceImpl implements PatientService {

    private ModelMapper modelMapper;

    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PatientResponse getPatients(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Patient> patients = patientRepository.findAll(pageable);
        // get content for page object
        List<Patient> listOfPatients = patients.getContent();

       List<PatientDto> content = listOfPatients.stream().map(patient -> mapToDto(patient)).collect(Collectors.toList());

        PatientResponse patientDto = new Patient();
        patientDto.setContent(content);
        patientDto.setLast(patients.isLast());
        patientDto.setTotalPages(patients.getTotalPages());
        patientDto.setPageSize(patients.getSize());
        patientDto.setPageNo(patients.getNumber());
        patientDto.setTotalElements(patients.getTotalElements());

        return modelMapper.map(patientDto, (Type) Patient.class);


    }

    @Override
    public PatientResponse getPatientById(Long patientId) {
        log.info("getting patient data: ");
        Patient patients =  patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", patientId)
        );
        return modelMapper.map(patients, (Type) Patient.class);
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
        patient.setMyDoctor(String.valueOf(patientDto.getMyDoctor()));
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
        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        return patientDto;
    }

    private Patient mapToEntity(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        return patient;
    }

}
