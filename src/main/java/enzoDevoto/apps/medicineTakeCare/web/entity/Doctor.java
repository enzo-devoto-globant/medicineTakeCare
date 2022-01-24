package enzoDevoto.apps.medicineTakeCare.web.entity;

import enzoDevoto.apps.medicineTakeCare.web.model.DoctorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
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
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "doctors", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"}),
@UniqueConstraint(columnNames = {"username"}),
@UniqueConstraint(columnNames = {"email"})}
)
public class Doctor extends DoctorResponse {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    ) private Long id;

    @Column(name = "speciality", nullable = false)
    private String speciality;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "rate", nullable = false)
    private Double rate;
    @Column(name = "price", nullable = false)
    private Long price;
    @Column(name = "doctor", nullable = false)
    private String name;
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "doctor_roles",
    joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}
