package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.elagin.hostel.dto.RoleDTO;
import ru.elagin.hostel.entities.Role;
import ru.elagin.hostel.service.impl.RoleServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
@PreAuthorize("hasRole('admin')")
public class RoleController {
    private final RoleServiceImpl roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }

    @DeleteMapping("/{role_id}")
    public void deleteRole(@PathVariable("role_id") Long id) {
        roleService.deleteRole(id);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return roleService.getAllRoles();
    }
}
