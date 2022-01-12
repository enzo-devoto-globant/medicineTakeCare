package enzoDevoto.apps.medicineTakeCare.web.entity;

import enzoDevoto.apps.medicineTakeCare.web.model.RoleResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Role extends RoleResponse {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(length = 60)
    private String name;
}
