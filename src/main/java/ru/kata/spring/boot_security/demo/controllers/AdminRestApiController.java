package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestApiController {

    private final UserService userService;

    private final RoleRepository roleRepository;

    @Autowired
    public AdminRestApiController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> printAllUsers() {
        List<User> allUsers = userService.getAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/currentAdminUser")
    public ResponseEntity<User> printAuthAdminUser(Principal principal) {
        User adminAuthUser = userService.findByName(principal.getName());
        return new ResponseEntity<>(adminAuthUser, HttpStatus.OK);
    }

    // Подумать нужно или нет
    @GetMapping("/user/{id}")
    public ResponseEntity<User> printUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Подумать нужно или нет
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> printAllRoles() {
        List<Role> allRoles = roleRepository.findAll();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }

    // Подумать нужно или нет
    @GetMapping("/roles/{id}")
    public ResponseEntity<List<Role>> printRole (@PathVariable Long id) {
        List<Role> role = userService.findById(id).getRoles();
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/allUsers")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/allUsers/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {
        userService.update(updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/allUsers/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@RequestBody User deletedUser) {
        userService.delete(deletedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

