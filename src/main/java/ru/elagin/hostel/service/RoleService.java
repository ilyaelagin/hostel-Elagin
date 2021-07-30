package ru.elagin.hostel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.dto.RoleDTO;
import ru.elagin.hostel.entities.Role;
import ru.elagin.hostel.exception.RepositoryException;
import ru.elagin.hostel.implementation.RoleServiceImpl;
import ru.elagin.hostel.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleServiceImpl {
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<RoleDTO> createRole(RoleDTO roleDTO) {
        Role createdRole = Optional.of(roleRepository.save(new Role(roleDTO))).orElseThrow(
                () -> new RepositoryException("Role not saved!"));

        return ResponseEntity.ok(new RoleDTO(createdRole));
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roleList = roleRepository.findAll();
        if (roleList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(roleList);
        }
    }
}
