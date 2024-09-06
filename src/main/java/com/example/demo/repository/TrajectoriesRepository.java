package com.example.demo.repository;

import com.example.demo.model.Trajectories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrajectoriesRepository extends JpaRepository<Trajectories, Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM public.trajectories WHERE taxi_id = :taxiId AND TO_CHAR(date, 'dd-MM-yyyy') = :date")
    Page<Trajectories> findTrajectoryByTaxiIdAndDate(Integer taxiId, String date, Pageable pageable);


}