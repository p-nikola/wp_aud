package com.example.aud1.repository.impl;

import com.example.aud1.bootstrap.DataHolder;
import com.example.aud1.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {

    public List<Manufacturer> findAll() {
        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id) {
        return DataHolder.manufacturers.stream().filter(i -> i.getId().equals(id)).findFirst();
    }


}
