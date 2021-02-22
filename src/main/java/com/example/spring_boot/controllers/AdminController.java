package com.example.spring_boot.controllers;

import com.example.spring_boot.model.User;
import com.example.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping
    public String adminPage(Model model) {
        List<User> list = userService.listUsers();
        model.addAttribute("users", list);
        return "admin";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/{id}/edit")
    public String changeUserPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", userService.listRoles());
        return "edit";
    }

    @PostMapping("{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("role_select") Long[] roleIds) {
        for (Long id : roleIds) {
            user.addRole(userService.getRoleById(id));
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String addUserPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", userService.listRoles());
        return "new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam("role_select") Long[] roleIds) {
        for (Long id : roleIds) {
            user.addRole(userService.getRoleById(id));
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String delUser(@RequestParam("user_id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
