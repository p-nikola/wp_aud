package com.example.aud1.web.controller;

import com.example.aud1.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoleController {

    private final UserService userService; // Assume a service to manage users

    public RoleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/change-role")
    @PreAuthorize("hasRole('ADMIN')") // Ensure only admins can access this
    public String getChangeRolePage(Model model) {
        model.addAttribute("bodyContent","change-role");
        return "master-template";
    }

    @PostMapping("/change-role")
    @PreAuthorize("hasRole('ADMIN')") // Ensure only admins can perform this action
    public String changeUserRole(@RequestParam String username, @RequestParam String role) {
        try {
            userService.changeRole(username, role); // Update the user's role
            return "redirect:/change-role?success";
        } catch (RuntimeException ex) {
            return "redirect:/change-role?error=" + ex.getMessage();
        }
    }

}
