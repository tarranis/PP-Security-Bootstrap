package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping()
//    public String printAllUser(Model model) {
//        model.addAttribute("users", userService.getAll());
//        return "user/allUsers";
//    }
//
//    @GetMapping("/newUser")
//    public String showNewUser(@ModelAttribute("newuser") User user) {
//        return "user/newUser";
//    }
//
//    @PostMapping()
//    public String addToDB(@ModelAttribute("newuser") User user) {
//        userService.save(user);
//        return "redirect:/user";
//    }
//
//    @GetMapping("/delete")
//    public String showDeleteUser() {
//        return "user/delete";
//    }
//
//    @PostMapping("/delete")
//    public String deleteFromDB(@RequestParam(name = "id") Long id) {
//        User user = userService.findById(id);
//        if (user != null) {
//            userService.delete(user);
//        } else {
//            System.out.println("Такого пользователя нет");
//        }
//        return "redirect:/user";
//    }
//
//    @GetMapping("/update")
//    public String showUpdateUser() {
//        return "user/update";
//    }
//
//    @PostMapping("/update")
//    public String updateUserFromDB(@RequestParam(name = "id") Long id,@RequestParam(name = "name") String name,@RequestParam(name = "lastName") String lastname,@RequestParam(name = "age") int age, Model model) {
//        User user = userService.findById(id);
//        if (user != null) {
//            user.setName(name);
//            user.setLastName(lastname);
//            user.setAge(age);
//        } else {
//            System.out.println("Такого пользователя нет");
//        }
//        User updateuser = userService.update(user);
//        model.addAttribute("updateuser", updateuser);
//        return "redirect:/user";
//    }

}
