package ru.elagin.hostel.entities;

import lombok.Data;
import ru.elagin.hostel.dto.RoleDTO;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles", schema = "HOSTEL")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Role() {
    }

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
