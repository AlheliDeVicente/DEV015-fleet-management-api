package com.example.demo.service;

import com.example.demo.model.Trajectories;
import com.example.demo.repository.TaxiRepository;
import com.example.demo.repository.TrajectoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.sql.Date;
@Service
public class TrajectoriesService {
    private final TrajectoriesRepository trajectoriesRepository;
    private final TaxiRepository taxiRepository;
    @Autowired
    public TrajectoriesService(TrajectoriesRepository trajectoriesRepository, TaxiRepository taxiRepository) {
        this.trajectoriesRepository = trajectoriesRepository;
        this.taxiRepository = taxiRepository;
    }

    public Page<Trajectories> findAllTrajectories(Integer taxiId, String date, Pageable pageable) throws FileNotFoundException {
        if (taxiId != null) {
            return trajectoriesRepository.findTrajectoryByTaxiIdAndDate(taxiId, date, pageable);
        }
        else {
            return trajectoriesRepository.findAll(pageable);
        }
    }

}
