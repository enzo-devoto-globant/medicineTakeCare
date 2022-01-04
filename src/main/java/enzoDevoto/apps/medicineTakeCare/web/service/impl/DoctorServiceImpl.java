package enzoDevoto.apps.medicineTakeCare.web.service.impl;

import enzoDevoto.apps.medicineTakeCare.web.entity.Doctor;
import enzoDevoto.apps.medicineTakeCare.web.exception.ResourceNotFoundException;
import enzoDevoto.apps.medicineTakeCare.web.model.DoctorDto;
import enzoDevoto.apps.medicineTakeCare.web.model.DoctorResponse;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientDto;
import enzoDevoto.apps.medicineTakeCare.web.repository.DoctorRepository;
import enzoDevoto.apps.medicineTakeCare.web.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DoctorResponse getDoctors(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()  : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Doctor> doctors = doctorRepository.findAll(pageable);

        List<Doctor> listOfDoctors = doctors.getContent();

        List<DoctorDto> content = listOfDoctors.stream().map(doctor -> mapToDto(doctor)).collect(Collectors.toList());

        DoctorResponse doctorResponse = new Doctor();
        doctorResponse.setContent(content);
        doctorResponse.setLast(doctors.isLast());
        doctorResponse.setTotalPages(doctors.getTotalPages());
        doctorResponse.setPageSize(doctors.getSize());
        doctorResponse.setPageNo(doctors.getNumber());
        doctorResponse.setTotalElements(doctors.getTotalElements());
        return modelMapper.map(doctorResponse, (Type) Doctor.class);

    }

    @Override
    public DoctorResponse getDoctorById(Long doctorId) {
        log.info("getting patient data: ");
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("post", "id", doctorId));
        return modelMapper.map(doctor, Doctor.class);
    }

    @Override
    public DoctorDto setNewDoctorDto(DoctorDto doctorDto) {
        log.info("Creating doctor data: ");
        Doctor doctorRequest = mapToEntity(doctorDto);
        Doctor newDoctor = doctorRepository.save(doctorRequest);
        //convert entity to dto
        DoctorDto doctorResponse = mapToDto(newDoctor);
        return doctorResponse;
    }

    @Override
    public DoctorDto updateDoctor(Long doctorDtoId, DoctorDto doctorDto) {
        Doctor doctor = doctorRepository.findById(doctorDtoId).orElseThrow(() -> new ResourceNotFoundException("post", "id", doctorDtoId));
        doctor.setAddress(doctorDto.getAddress());
        doctor.setId(doctorDto.getId());
        doctor.setGender(doctorDto.getGender());
        doctor.setPhoneNumber(doctorDto.getPhoneNumber());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setDescription(doctorDto.getDescription());
        doctor.setPrice(doctorDto.getPrice());
        doctor.setAge(doctorDto.getAge());
        doctor.setName(doctorDto.getName());
        doctor.setRate(doctorDto.getRate());
        doctor.setSpeciality(doctorDto.getSpeciality());
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return mapToDto(updatedDoctor);
    }

    @Override
    public void deleteDoctor(Long doctorDtoId) {
        log.debug("Deleting a doctor by ID");
        Doctor doctor = doctorRepository.findById(doctorDtoId).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", doctorDtoId));
        doctorRepository.delete(doctor);
    }

    private DoctorDto mapToDto(Doctor doctor) {
        DoctorDto doctorResponse = modelMapper.map(doctor, DoctorDto.class);
        return doctorResponse;
    }

    private Doctor mapToEntity(DoctorDto doctorDto) {
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);
        return doctor;
    }
}
