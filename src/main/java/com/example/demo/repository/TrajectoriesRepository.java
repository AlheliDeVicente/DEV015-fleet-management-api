package com.example.demo.repository;

import com.example.demo.model.Trajectories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrajectoriesRepository extends JpaRepository<Trajectories, Integer> {
}
