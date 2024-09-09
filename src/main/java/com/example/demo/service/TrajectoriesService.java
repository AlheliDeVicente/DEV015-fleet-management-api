package com.example.demo.service;

import com.example.demo.DTO.TrajectoriesDTO;
import com.example.demo.model.Trajectories;
import com.example.demo.repository.TaxiRepository;
import com.example.demo.repository.TrajectoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class TrajectoriesService {
    private final TrajectoriesRepository trajectoriesRepository;
    private final TaxiRepository taxiRepository;

    @Autowired
    public TrajectoriesService(TrajectoriesRepository trajectoriesRepository, TaxiRepository taxiRepository) {
        this.trajectoriesRepository = trajectoriesRepository;
        this.taxiRepository = taxiRepository;
    }

    private TrajectoriesDTO convertToDTO(Trajectories trajectories){
        TrajectoriesDTO dto = new TrajectoriesDTO();
        dto.setId(trajectories.getId());
        dto.setTaxiId(trajectories.getTaxiId().getId());
        dto.setPlate(trajectories.getTaxiId().getPlate());
        dto.setDate(trajectories.getDate());
        dto.setLatitude(trajectories.getLatitude());
        dto.setLongitude(trajectories.getLongitude());
        return dto;
    }

    public Page<TrajectoriesDTO> findAllTrajectories(Integer taxiId, String date, Pageable pageable) {
        Page<Trajectories> trajectoriesPage;

        if (taxiId != null) {
            trajectoriesPage = trajectoriesRepository.findTrajectoryByTaxiIdAndDate(taxiId, date, pageable);
        } else {
            trajectoriesPage = trajectoriesRepository.findAll(pageable);
        }
        return trajectoriesPage.map(this::convertToDTO);
    }
}
