package com.example.aud1.service.impl;

import com.example.aud1.model.Category;
import com.example.aud1.repository.InMemoryCategoryRepository;
import com.example.aud1.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceimpl implements CategoryService {

    private final InMemoryCategoryRepository categoryRepository;


    public CategoryServiceimpl(InMemoryCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category c = new Category(name, description);
        categoryRepository.save(c);

        return c;

    }

    @Override
    public Category update(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category c = new Category(name, description);
        categoryRepository.save(c);

        return c;
    }

    @Override
    public void delete(String name) {
       if(name==null || name.isEmpty()) {
           throw new IllegalArgumentException();
       }

       categoryRepository.delete(name);


    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String text) {
        return categoryRepository.search(text);
    }
}
