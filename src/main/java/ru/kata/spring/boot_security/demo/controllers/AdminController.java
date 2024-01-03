package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printAllUser(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin/allUsers";
    }

    @GetMapping("/newUser")
    public String showNewUser(@ModelAttribute("newuser") User user) {
        return "admin/newUser";
    }

    @PostMapping()
    public String addToDB(@ModelAttribute("newuser") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String showDeleteUser() {
        return "admin/delete";
    }

    @PostMapping("/delete")
    public String deleteFromDB(@RequestParam(name = "id") Long id) {
        User user = userService.findById(id);
        if (user != null) {
            userService.delete(user);
        } else {
            System.out.println("Такого пользователя нет");
        }
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String showUpdateUser() {
        return "admin/update";
    }

    @PostMapping("/update")
    public String updateUserFromDB(@RequestParam(name = "id") Long id,@RequestParam(name = "name") String name,@RequestParam(name = "lastName") String lastname,@RequestParam(name = "age") int age, Model model) {
        User user = userService.findById(id);
        if (user != null) {
            user.setName(name);
            user.setLastName(lastname);
            user.setAge(age);
        } else {
            System.out.println("Такого пользователя нет");
        }
        User updateuser = userService.update(user);
        model.addAttribute("updateuser", updateuser);
        return "redirect:/admin";
    }
}
