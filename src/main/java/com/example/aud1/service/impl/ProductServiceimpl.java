package com.example.aud1.service.impl;

import com.example.aud1.model.Category;
import com.example.aud1.model.Manufacturer;
import com.example.aud1.model.Product;
import com.example.aud1.model.exceptions.CategoryNotFoundException;
import com.example.aud1.model.exceptions.ManufacturerNotFoundException;
import com.example.aud1.repository.InMemoryCategoryRepository;
import com.example.aud1.repository.InMemoryManufacturerRepository;
import com.example.aud1.repository.InMemoryProductRepository;
import com.example.aud1.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceimpl implements ProductService {


    private final InMemoryProductRepository productRepository;
    private final InMemoryCategoryRepository categoryRepository;
    private final InMemoryManufacturerRepository manufacturerRepository;

    public ProductServiceimpl(InMemoryProductRepository productRepository, InMemoryCategoryRepository categoryRepository, InMemoryManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));


        return this.productRepository.save(name, price, quantity, category, manufacturer);

    }

    @Override
    public void deleteById(Long id) {

        productRepository.deleteById(id);

    }
}
