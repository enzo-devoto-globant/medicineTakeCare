package enzoDevoto.apps.medicineTakeCare.web.entity;


import enzoDevoto.apps.medicineTakeCare.web.model.DoctorDto;
import enzoDevoto.apps.medicineTakeCare.web.model.PatientResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "patients", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})}
)
public class Patient extends PatientResponse {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    ) private Long id;

    @Column(name = "patient", nullable = false)
   private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "timeOfEvaluation")
    private Date timeOfEvaluation;
    @Column(name = "isCritical", nullable = false)
    private boolean isCritical;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phoneNumber", nullable = false)
    private Long phoneNumber;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name= "username", nullable = false)
    private String username;
    @Column(name= "password", nullable = false)
    private String password;

 @ManyToMany(fetch = FetchType.EAGER)
 @JoinTable(name = "my_doctors",
         joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"))
 private Set<Doctor> myDoctors;
}
