package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

    @GetMapping("/all-roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return roleService.getAllRoles();
    }
}
