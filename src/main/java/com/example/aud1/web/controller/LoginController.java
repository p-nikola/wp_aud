package com.example.aud1.web.controller;


import com.example.aud1.model.User;
import com.example.aud1.model.exceptions.InvalidUserCredentialException;
import com.example.aud1.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    //@RequestMapping(method = RequestMethod.GET,value = "/login")
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent","login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {

        User user = null;

        try {

            user = this.authService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);

            return "redirect:/home";

        } catch (InvalidUserCredentialException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error",exception.getMessage());
            return "login";
        }


    }
}
