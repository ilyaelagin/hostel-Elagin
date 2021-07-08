package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.elagin.hostel.dto.UserDTO;
import ru.elagin.hostel.entities.User;
import ru.elagin.hostel.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/{user_id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteUser(@PathVariable("user_id") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/role")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<User> setUserRole(@RequestBody Map<String, String> map) {
        return userService.setUserRole(map);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }
}
