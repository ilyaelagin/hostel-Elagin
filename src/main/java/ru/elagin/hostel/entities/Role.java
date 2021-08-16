package ru.elagin.hostel.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.elagin.hostel.dto.RoleDTO;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles", schema = "HOSTEL")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Role(RoleDTO roleDTO) {
        if (roleDTO == null) {
            throw new IllegalArgumentException("Role dos not exist");
        }
        if (roleDTO.getId() != null) {
            this.id = Long.valueOf(roleDTO.getId());
        }
        if (roleDTO.getName() != null) {
            this.name = roleDTO.getName();
        }
    }
}
