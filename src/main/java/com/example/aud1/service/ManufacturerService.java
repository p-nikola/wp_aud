package com.example.aud1.service;

import com.example.aud1.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long id);

    void deleteById(Long id);
}
