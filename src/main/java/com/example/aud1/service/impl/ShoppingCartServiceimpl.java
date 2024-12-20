package com.example.aud1.service.impl;

import com.example.aud1.model.Product;
import com.example.aud1.model.ShoppingCart;
import com.example.aud1.model.User;
import com.example.aud1.model.enumeration.ShoppingCartStatus;
import com.example.aud1.model.exceptions.ProductAlreadyInShoppingCartException;
import com.example.aud1.model.exceptions.ProductNotFoundException;
import com.example.aud1.model.exceptions.ShoppingCartNotFoundException;
import com.example.aud1.model.exceptions.UserNotFoundException;
import com.example.aud1.repository.impl.InMemoryShoppingCartRepository;
import com.example.aud1.repository.impl.InMemoryUserRepository;
import com.example.aud1.repository.jpa.ShoppingCartRepository;
import com.example.aud1.repository.jpa.UserRepository;
import com.example.aud1.service.ProductService;
import com.example.aud1.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceimpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceimpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cardId) {
        if (this.shoppingCartRepository.findById(cardId).isEmpty()) {
            throw new ShoppingCartNotFoundException(cardId);
        }
        return shoppingCartRepository.findById(cardId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        //User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
       return this.shoppingCartRepository.findByUserNameAndStatus(username,ShoppingCartStatus.CREATED).orElseGet(()->{
           User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
            ShoppingCart cart = new ShoppingCart(user);
            return this.shoppingCartRepository.save(cart);
        });


    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = getActiveShoppingCart(username);

        Product product = productService.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        if (shoppingCart.getProducts().stream().filter(i -> i.getId().equals(productId)).collect(Collectors.toList()).size() > 0) {
            throw new ProductAlreadyInShoppingCartException(productId, username);
        }
        shoppingCart.getProducts().add(product);
        return shoppingCartRepository.save(shoppingCart);
    }
}
