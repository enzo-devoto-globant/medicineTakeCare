package enzoDevoto.apps.medicineTakeCare.web.controller;

import enzoDevoto.apps.medicineTakeCare.web.entity.Doctor;
import enzoDevoto.apps.medicineTakeCare.web.entity.Role;
import enzoDevoto.apps.medicineTakeCare.web.model.LoginDto;
import enzoDevoto.apps.medicineTakeCare.web.model.SignUpDto;
import enzoDevoto.apps.medicineTakeCare.web.repository.DoctorRepository;
import enzoDevoto.apps.medicineTakeCare.web.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    public DoctorRepository doctorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticatedUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody SignUpDto signUpDto) {
        if (doctorRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity("Username is already taken. ", HttpStatus.BAD_REQUEST);
        }
        if (doctorRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity("Email already exists. ", HttpStatus.BAD_REQUEST);
        }

        Doctor doctor = new Doctor();
        doctor.setName(signUpDto.getName());
        doctor.setUsername(signUpDto.getUsername());
        doctor.setEmail(signUpDto.getEmail());
        doctor.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        doctor.setRoles(Collections.singleton(roles));
        doctorRepository.save(doctor);
        return new ResponseEntity("User successfully registered! ", HttpStatus.OK);

    }
}
