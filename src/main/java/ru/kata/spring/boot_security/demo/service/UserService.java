package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.configs.WebSecurityConfig;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User update(User user) {
        User desiredUser = userRepository.findByName(user.getName());
        if (desiredUser == null) {
            System.out.println("Пользователь не найден");
            return null;
        }
        desiredUser.setName(user.getName());
        desiredUser.setLastName(user.getLastName());
        desiredUser.setAge(user.getAge());
        return userRepository.save(desiredUser);
    }

    public boolean save(User user) {
        User desiredUser = userRepository.findByName(user.getName());
        if (desiredUser != null) {
            System.out.println("Такой пользователь уже существует");
            return false;
        }
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        user.setPassword(WebSecurityConfig.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean delete(User user) {
        User desiredUser = userRepository.findByName(user.getName());
        if (desiredUser == null) {
            System.out.println("Пользователь не найден");
            return false;
        }
        userRepository.delete(desiredUser);
        return true;
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        Optional<User> desiredUser = userRepository.findById(id);
        return desiredUser.orElse(new User());
    }

    @Transactional(readOnly = true)
    public User findByName(String name) {
        Optional<User> desiredUser = Optional.ofNullable(userRepository.findByName(name));
        return desiredUser.orElse(null);
    }

}
