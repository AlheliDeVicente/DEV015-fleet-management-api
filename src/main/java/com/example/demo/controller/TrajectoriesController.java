package com.example.demo.controller;

import com.example.demo.DTO.TrajectoriesDTO;
import com.example.demo.service.TrajectoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class TrajectoriesController {
    @Autowired
    private TrajectoriesService trajectoriesService;

    @GetMapping("/trajectories")
    private ResponseEntity<List<TrajectoriesDTO>> getAllTrajectories(@RequestParam(required = false) Integer taxiId, @RequestParam(required = false) String date, Pageable pageable) {
        Page <TrajectoriesDTO> allTrajectories = trajectoriesService.findAllTrajectories(taxiId, date, pageable);
        return new ResponseEntity<>(allTrajectories.getContent(),HttpStatus.OK);
    }

    @GetMapping("/trajectories/latest")
    private ResponseEntity<List<TrajectoriesDTO>> getLatestTrajectory(Pageable pageable){
        Page <TrajectoriesDTO> lastLocations = trajectoriesService.findLastTrajectory(pageable);
        return new ResponseEntity<>(lastLocations.getContent(), HttpStatus.OK);
    }
}
