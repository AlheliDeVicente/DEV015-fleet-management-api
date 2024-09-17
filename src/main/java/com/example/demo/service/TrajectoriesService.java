package com.example.demo.service;

import com.example.demo.DTO.TrajectoriesDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.model.Trajectories;
import com.example.demo.repository.TaxiRepository;
import com.example.demo.repository.TrajectoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageImpl;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrajectoriesService {
    private final TrajectoriesRepository trajectoriesRepository;
    private final TaxiRepository taxiRepository;

    @Autowired
    public TrajectoriesService(TrajectoriesRepository trajectoriesRepository, TaxiRepository taxiRepository) {
        this.trajectoriesRepository = trajectoriesRepository;
        this.taxiRepository = taxiRepository;
    }

    private TrajectoriesDTO convertToDTO(Trajectories trajectories) {
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
        if(taxiId == null || date == null || date.isEmpty()){
            throw new BadRequestException("Please provide a taxiId and a date");
        }
        Page<Trajectories> trajectoriesPage = trajectoriesRepository.findTrajectoryByTaxiIdAndDate(taxiId, date, pageable);
        if(trajectoriesPage.isEmpty()){
            throw new DataNotFoundException("No trajectory found for taxi " + taxiId + " on date " + date);
        }
        List<TrajectoriesDTO> dtoList = new ArrayList<>();
        for (Trajectories trajectory : trajectoriesPage.getContent()) {
            TrajectoriesDTO dto = convertToDTO(trajectory);
            dtoList.add(dto);
        }
        return new PageImpl<>(dtoList, pageable, trajectoriesPage.getTotalElements());
    }
}