package com.example.demo.service;

import com.example.demo.model.Taxi;
import com.example.demo.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class TaxiService {

    private final TaxiRepository taxiRepository;

    @Autowired
    public TaxiService(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public Page<Taxi> findAllTaxis(String plate, Pageable pageable) throws FileNotFoundException {
        if (plate != null && !plate.isEmpty()) {
            return taxiRepository.findAllByPlateContainingIgnoreCase(plate, pageable);
        }
        else {
            return taxiRepository.findAll(pageable);
        }
    }
}

