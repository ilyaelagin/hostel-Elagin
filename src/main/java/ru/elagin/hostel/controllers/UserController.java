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
@PreAuthorize("hasRole('admin')")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable("user_id") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/role")
    public ResponseEntity<User> setUserRole(@RequestBody Map<String, String> map) {
        return userService.setUserRole(map);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/status")
    public ResponseEntity<String> setUserStatus(@RequestBody Map<String, String> map) {
        return userService.setUserStatus(map);
    }
}
