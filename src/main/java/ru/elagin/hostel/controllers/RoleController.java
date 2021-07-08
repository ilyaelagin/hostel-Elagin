package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.elagin.hostel.dto.RoleDTO;
import ru.elagin.hostel.entities.Role;
import ru.elagin.hostel.service.RoleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }

    @DeleteMapping("/{role_id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteRole(@PathVariable("role_id") Long id) {
        roleService.deleteRole(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<Role>> getAllRoles() {
        return roleService.getAllRoles();
    }
}
