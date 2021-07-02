package ru.elagin.hostel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.dto.RoleDTO;
import ru.elagin.hostel.entities.Role;
import ru.elagin.hostel.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public ResponseEntity<RoleDTO> createRole(RoleDTO roleDTO) {
        Role createdRole = roleRepository.save(new Role(roleDTO));
        if (createdRole.getId() == null) {
            throw new IllegalArgumentException("Role not saved!");
        }
        return ResponseEntity.ok(new RoleDTO(createdRole));
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        if (roleList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(roleList);
        }
    }
}
