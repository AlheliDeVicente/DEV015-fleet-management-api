package com.example.demo.repository;

import com.example.demo.model.Taxi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Integer> {

    Page<Taxi> findAllByPlateContainingIgnoreCase(String plate, Pageable pageable);
}
