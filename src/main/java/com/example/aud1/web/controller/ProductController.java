package com.example.aud1.web.controller;


import com.example.aud1.model.Category;
import com.example.aud1.model.Manufacturer;
import com.example.aud1.model.Product;
import com.example.aud1.service.CategoryService;
import com.example.aud1.service.ManufacturerService;
import com.example.aud1.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products = productService.findAll();

        model.addAttribute("products", products);

        return "products";

    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";

    }

    @GetMapping("/add-form")
    public String addProductPage(Model model) {
        List<Category> categories = this.categoryService.listCategories();
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("manufacturers", manufacturers);

        return "add-product";
    }


    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {

        if (this.productService.findById(id).isPresent()) {
            Product product = productService.findById(id).get();

            List<Category> categories = this.categoryService.listCategories();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();

            model.addAttribute("categories", categories);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("product", product);

            return "add-product";

        }

        return "redirect:/products?error=ProductNotFound";

    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam String name, @RequestParam Double price, @RequestParam Integer quantity, @RequestParam Long category, @RequestParam Long manufacturer) {
        productService.save(name, price, quantity, category, manufacturer);

        return "redirect:/products";

    }

}