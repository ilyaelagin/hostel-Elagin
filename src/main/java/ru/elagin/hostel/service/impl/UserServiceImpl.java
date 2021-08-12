package ru.elagin.hostel.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.elagin.hostel.check.CheckMatches;
import ru.elagin.hostel.dto.UserDTO;
import ru.elagin.hostel.entities.Role;
import ru.elagin.hostel.entities.User;
import ru.elagin.hostel.exception.RepositoryException;
import ru.elagin.hostel.repository.RoleRepository;
import ru.elagin.hostel.repository.UserRepository;
import ru.elagin.hostel.service.UserService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        Optional<User> userByLogin = userRepository.findByLogin(userDTO.getLogin());
        if (userByLogin.isPresent()) {
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
            Role role = roleRepository.findById(id).orElseThrow(() -> new RepositoryException(
                    "Role with id " + id + " does not exist in the database"));
            rolesSet.add(role);
        }
        User user = new User(userDTO, rolesSet);
        User createdUser = Optional.of(userRepository.save(user)).orElseThrow(() -> new RepositoryException("User not saved!"));

        return ResponseEntity.ok(new UserDTO(createdUser));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<User> setUserRole(Map<String, String> userIdRoleId) {
        Map<String, Long> map = CheckMatches.checkMatchesUserIdRoleId(userIdRoleId);
        Role role = roleRepository.findById(map.get("roleId")).orElseThrow(
                () -> new RepositoryException("The role does not exist!"));
        User userToUpdate = userRepository.findById(map.get("userId")).orElseThrow(
                () -> new RepositoryException("The user does not exist!"));

        userToUpdate.getRoles().add(role);
        userRepository.save(userToUpdate);

        return ResponseEntity.ok(userToUpdate);
    }

    @Override
    public ResponseEntity<User> deleteUserRole(Map<String, String> userIdRoleId) {
        Map<String, Long> map = CheckMatches.checkMatchesUserIdRoleId(userIdRoleId);
        Role role = roleRepository.findById(map.get("roleId")).orElseThrow(
                () -> new RepositoryException("The role does not exist!"));
        User userToUpdate = userRepository.findById(map.get("userId")).orElseThrow(
                () -> new RepositoryException("The user does not exist!"));
        if (!userToUpdate.getRoles().remove(role)) {
            throw new RepositoryException("The user does not have such a role!");
        }
        userRepository.save(userToUpdate);

        return ResponseEntity.ok(userToUpdate);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userList);
        }
    }

    @Override
    public ResponseEntity<String> setUserStatus(Map<String, String> userIdStatus) {
        Map<String, String> map = CheckMatches.checkMatchesUserIdStatus(userIdStatus);
        Long userId = Long.valueOf(map.get("userId"));
        String status = map.get("status");
        User userToUpdate = userRepository.findById(userId).orElseThrow(() -> new RepositoryException("The user does not exist!"));
        userToUpdate.setStatus(status);
        userRepository.save(userToUpdate);

        return ResponseEntity.ok(status);
    }
}
