package enzoDevoto.apps.medicineTakeCare.web.security;

import enzoDevoto.apps.medicineTakeCare.web.entity.Doctor;
import enzoDevoto.apps.medicineTakeCare.web.entity.Role;
import enzoDevoto.apps.medicineTakeCare.web.repository.DoctorRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomDoctorDetailsService implements UserDetailsService {

    private DoctorRepository doctorRepository;

    public CustomDoctorDetailsService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(
                () -> new UsernameNotFoundException("Username or email not found for: " + usernameOrEmail)
        );
        return new User(doctor.getEmail(), doctor.getPassword(), mapRolesforDoctors(doctor.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesforDoctors(Set<Role> roles){
     return roles.stream().map(
             role -> new SimpleGrantedAuthority(role.getName())
     ).collect(Collectors.toList());

    }

}
