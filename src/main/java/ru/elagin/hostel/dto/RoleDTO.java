package ru.elagin.hostel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.elagin.hostel.entities.Role;

@Data
@NoArgsConstructor
public class RoleDTO {
    private String id;
    private String name;

    public RoleDTO(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role dos not exist");
        }
        if (role.getId() != null) {
            this.id = String.valueOf(role.getId());
        }
        if (role.getName() != null) {
            this.name = role.getName();
        }
    }
}

