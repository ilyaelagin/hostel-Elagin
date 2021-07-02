package ru.elagin.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/add-role")
    public ResponseEntity<User> setUserRole(@RequestBody Map<String, String> map) {
        return userService.setUserRole(map);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }
}
