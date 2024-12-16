package com.example.aud1.web.controller;

import com.example.aud1.model.ShoppingCart;
import com.example.aud1.model.User;
import com.example.aud1.service.ShoppingCartService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;


    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
//DONT NEED THIS ANYMORE
//        User user = (User) req.getSession().getAttribute("user");
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = shoppingCartService.getActiveShoppingCart(username);

        model.addAttribute("products", shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req) {
        try {
            //User user = (User) req.getSession().getAttribute("user");
            String username= req.getRemoteUser();
            ShoppingCart shoppingCart = shoppingCartService.addProductToShoppingCart(username, id);

            return "redirect:/shopping-cart";

        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }


    }


}
