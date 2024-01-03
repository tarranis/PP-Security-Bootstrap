package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printUser(Model model, Principal principal) {
        User currentUser = userService.findByName(principal.getName());
        if (currentUser != null) {
            model.addAttribute("currentuser", currentUser);
        } else {
            System.out.println("Пользователь не найден");
        }
        return "user/user";
    }
}
