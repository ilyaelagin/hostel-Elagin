package ru.elagin.hostel.service;

import org.springframework.http.ResponseEntity;
import ru.elagin.hostel.dto.RoleDTO;
import ru.elagin.hostel.entities.Role;

import java.util.List;

public interface RoleService {

    ResponseEntity<RoleDTO> createRole(RoleDTO roleDTO);

    void deleteRole(Long id);

    ResponseEntity<List<Role>> getAllRoles();
}
