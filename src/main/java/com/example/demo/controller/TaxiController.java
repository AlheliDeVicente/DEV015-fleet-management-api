package com.example.demo.controller;

import com.example.demo.model.Taxi;
import com.example.demo.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController

public class TaxiController {

    @Autowired
    private TaxiService taxiService;

    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @GetMapping("/taxis")
    public Page<Taxi> getAllTaxis (@RequestParam(required = false) String plate, Pageable pageable) throws FileNotFoundException {
        try {
            return taxiService.findAllTaxis(plate, pageable);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


