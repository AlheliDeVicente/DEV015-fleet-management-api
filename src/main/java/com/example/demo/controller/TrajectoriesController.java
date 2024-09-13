package com.example.demo.controller;

import com.example.demo.DTO.TrajectoriesDTO;
import com.example.demo.service.TrajectoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import java.io.FileNotFoundException;

@RestController
public class TrajectoriesController {
    @Autowired
    private TrajectoriesService trajectoriesService;

    @GetMapping("/trajectories")
    public Page<TrajectoriesDTO> getAllTrajectories(@RequestParam(required = false) Integer taxi_id, @RequestParam(required = false) String date, Pageable pageable) throws FileNotFoundException {
        return trajectoriesService.findAllTrajectories(taxi_id, date, pageable);
    }

}
