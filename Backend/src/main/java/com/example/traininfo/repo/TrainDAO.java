package com.example.traininfo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.traininfo.entity.Train;

@Repository
public interface TrainDAO extends JpaRepository<Train, Long> {

}
