package com.example.aud1.bootstrap;

import com.example.aud1.model.*;
import com.example.aud1.repository.jpa.*;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public static List<Category> categories = new ArrayList<>();
    public static List<User> users= new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

    public DataHolder(CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository, ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        if (this.categoryRepository.count() == 0) {
            categories.add(new Category("Sports", "Sports category"));
            categories.add(new Category("Food", "Food category"));
            categories.add(new Category("Books", "Books category"));
            this.categoryRepository.saveAll(categories);
        }

        users = new ArrayList<>();
        if (this.userRepository.count() == 0) {
            users.add(new User("elena.atanasoska", passwordEncoder.encode("ea"), "Elena", "Atanasoska", Role.ROLE_USER));
            users.add(new User("darko.sasanski", passwordEncoder.encode("ds"), "Darko", "Sasanski", Role.ROLE_USER));
            users.add(new User("ana.todorovska", passwordEncoder.encode("at"), "Ana", "Todorovska", Role.ROLE_USER));
            users.add(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN));
            this.userRepository.saveAll(users);
        }

        manufacturers = new ArrayList<>();
        if (this.manufacturerRepository.count() == 0) {
            manufacturers.add(new Manufacturer("Nike", "USA"));
            manufacturers.add(new Manufacturer("Coca Cola", "USA"));
            manufacturers.add(new Manufacturer("Literatura", "MK"));
            this.manufacturerRepository.saveAll(manufacturers);
        }

        shoppingCarts = new ArrayList<>();
    }




//    @PostConstruct
//    public void init(){
//        categories.add(new Category("Software","Software desc"));
//        categories.add(new Category("Books","Books desc"));
//
//
//        users.add(new User("nikolakis.pet","np","nikola","pet"));
//        users.add(new User("kalinakis.pet","kp","kalin","pet"));
//        users.add(new User("nikola","np","nikola","pet"));
//
//        Manufacturer manufacturer = new Manufacturer("Nike","NY NY");
//        manufacturers.add(manufacturer);
//
//
//        Category category= new Category("Sport","Sport category");
//
//        categories.add(category);
//        products.add(new Product("Ball 1",235.8,7,category,manufacturer));
//        products.add(new Product("Ball 2",235.8,7,category,manufacturer));
//        products.add(new Product("Ball 3",235.8,7,category,manufacturer));
//
//        categoryRepository.saveAll(categories);
//        userRepository.saveAll(users);
//        manufacturerRepository.saveAll(manufacturers);
//        productRepository.saveAll(products);
//
//    }
}
