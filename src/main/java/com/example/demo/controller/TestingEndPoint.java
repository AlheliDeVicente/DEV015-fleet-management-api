package com.example.demo.controller;

import com.example.demo.model.Taxi;
import com.example.demo.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestingEndPoint {
    @Autowired
    private TaxiRepository taxirepository;

    @GetMapping("/taxis")
    public List<Taxi> test() {
        return this.taxirepository.findAll();
    }
}

