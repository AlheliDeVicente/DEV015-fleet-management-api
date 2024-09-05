package com.example.demo.repository;

import com.example.demo.model.Trajectories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface TrajectoriesRepository extends JpaRepository<Trajectories, Integer> {
    Page<Trajectories> findTrajectoryByIdAndDate(Integer taxiId, Date date, Pageable pageable);
}
