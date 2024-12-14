package com.example.aud1.service.impl;

import com.example.aud1.model.Manufacturer;
import com.example.aud1.repository.impl.InMemoryManufacturerRepository;
import com.example.aud1.repository.jpa.ManufacturerRepository;
import com.example.aud1.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceimpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceimpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }

    @Override
    public void deleteById(Long id){ this.manufacturerRepository.deleteById(id);}
}
