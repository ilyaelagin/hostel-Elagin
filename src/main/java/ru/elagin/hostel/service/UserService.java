package ru.elagin.hostel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.dto.UserDTO;
import ru.elagin.hostel.entities.Role;
import ru.elagin.hostel.entities.User;
import ru.elagin.hostel.repository.RoleRepository;
import ru.elagin.hostel.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        User userByLogin = userRepository.findByLogin(userDTO.getLogin()).orElse(null);
        if (userByLogin != null) {
            userDTO.setError("The user has not been saved! A user with such a login already exists in the database!");
            return ResponseEntity.ok(userDTO);
        }
        Set<Long> idRolesSet = new HashSet<>();
        Set<Role> rolesSet = new HashSet<>();
        for (String id : userDTO.getRolesId()) {
            if (!id.matches("\\d+")) {
                throw new IllegalArgumentException("Role id must be numeric!");
            }
            idRolesSet.add(Long.valueOf(id));
        }
        for (Long id : idRolesSet) {
            Role role = roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                    "Role with id " + id + " does not exist in the database"));
            rolesSet.add(role);
        }
        User user = new User(userDTO, rolesSet);
        User createdUser = userRepository.save(user);
        if (createdUser.getId() == null) {
            throw new IllegalArgumentException("User not saved!");
        }
        return ResponseEntity.ok(new UserDTO(createdUser));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public ResponseEntity<User> setUserRole(Map<String, String> userIdRoleId) {
        if (!userIdRoleId.get("userId").matches("\\d+")) {
            throw new IllegalArgumentException("User id must be numeric!");
        }
        Long userId = Long.valueOf(userIdRoleId.get("userId"));

        if (!userIdRoleId.get("roleId").matches("\\d+")) {
            throw new IllegalArgumentException("Role id must be numeric!");
        }
        Long roleId = Long.valueOf(userIdRoleId.get("roleId"));

        User userToUpdate = userRepository.findById(userId).orElse(null);
        if (userToUpdate == null) {
            throw new IllegalArgumentException("The user does not exist!");
        }
        Role role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            throw new IllegalArgumentException("The role does not exist!");
        }
        userToUpdate.getRoles().add(role);
        userRepository.save(userToUpdate);

        return ResponseEntity.ok(userToUpdate);
    }

    public ResponseEntity<User> deleteUserRole(Map<String, String> userIdRoleId) {
        if (!userIdRoleId.get("userId").matches("\\d+")) {
            throw new IllegalArgumentException("User id must be numeric!");
        }
        Long userId = Long.valueOf(userIdRoleId.get("userId"));

        if (!userIdRoleId.get("roleId").matches("\\d+")) {
            throw new IllegalArgumentException("Role id must be numeric!");
        }
        Long roleId = Long.valueOf(userIdRoleId.get("roleId"));

        User userToUpdate = userRepository.findById(userId).orElse(null);
        if (userToUpdate == null) {
            throw new IllegalArgumentException("The user does not exist!");
        }
        Role role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            throw new IllegalArgumentException("The role does not exist!");
        }

        if (!userToUpdate.getRoles().remove(role)) {
            throw new IllegalArgumentException("The user does not have such a role!");
        }
        userRepository.save(userToUpdate);

        return ResponseEntity.ok(userToUpdate);
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userList);
        }
    }

    public ResponseEntity<String> setUserStatus(Map<String, String> userIdStatus) {
        if (!userIdStatus.get("userId").matches("\\d+")) {
            throw new IllegalArgumentException("User id must be numeric!");
        }
        Long userId = Long.valueOf(userIdStatus.get("userId"));
        String status = userIdStatus.get("status");
        if ((!"active".equals(status)) && (!"banned".equals(status))) {
            throw new IllegalArgumentException("The status must be: active or banned");
        }
        User userToUpdate = userRepository.findById(userId).orElse(null);
        if (userToUpdate == null) {
            throw new IllegalArgumentException("The user does not exist!");
        }
        userToUpdate.setStatus(status);
        userRepository.save(userToUpdate);

        return ResponseEntity.ok(status);
    }
}
