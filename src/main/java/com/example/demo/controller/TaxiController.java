package com.example.demo.controller;

import com.example.demo.model.Taxi;
import com.example.demo.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController

public class TaxiController {

    @Autowired
    private TaxiService taxiService;

    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @GetMapping("/taxis")
    public ResponseEntity<List<Taxi>> getAllTaxis (@RequestParam(required = false) String plate, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit){
        Page<Taxi> Taxispage = taxiService.findAllTaxis(plate, page, limit);
        return new ResponseEntity<>(Taxispage.getContent(), HttpStatus.OK);
    }
}



