package com.example.aud1.service;

import com.example.aud1.model.Category;

import java.util.List;

public interface CategoryService {


    Category create(String name, String description);

    Category update(String name, String description);

    void delete(String name);

    List<Category> listCategories();

    List<Category> searchCategories(String text);


}
