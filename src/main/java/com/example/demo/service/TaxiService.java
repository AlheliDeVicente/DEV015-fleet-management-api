package com.example.demo.service;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.model.Taxi;
import com.example.demo.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Taxi> findAllTaxis(String plate, int page, int limit) {
        if(page <= 0){
            throw new BadRequestException("Page number has to be greater than zero");
        }
        Pageable pageable = PageRequest.of(page -1, limit);
        Page<Taxi> taxisPage;
        if(plate != null && !plate.isEmpty()){
            taxisPage = taxiRepository.findAllByPlateContainingIgnoreCase(plate, pageable);
            if (taxisPage.isEmpty()){
                throw new DataNotFoundException("No taxis found with plate containing " + plate);
            }
            return taxisPage;
        } else {
        taxisPage = taxiRepository.findAll(pageable);
        if(taxisPage.isEmpty()){
            throw new DataNotFoundException("No taxis found");
        }
        return taxisPage;
    }
}
}

