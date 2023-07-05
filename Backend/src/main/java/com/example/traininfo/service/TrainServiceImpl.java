package com.example.traininfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.traininfo.dto.TrainDTO;
import com.example.traininfo.entity.Train;
import com.example.traininfo.repo.TrainDAO;

@Service
public class TrainServiceImpl implements TrainService {
	
	@Autowired
	private final TrainDAO trainDAO;

	@Autowired
	public TrainServiceImpl(TrainDAO trainDAO) {
		this.trainDAO = trainDAO;
	}

	@Override
	public Train createTrain(TrainDTO trainDTO) {
		return null;
	}

	@Override
	public Train updateTrain(Long id, TrainDTO trainDTO) {
		return null;
	}

	@Override
	public boolean deleteTrain(Long id) {
		return false;
	}

	@Override
	public Train getTrainById(Long id) {
		return null;
	}

	@Override
	public Train getTrainByNumber(Integer number) {
		return null;
	}

	@Override
	public List<Train> searchTrainsByName(String name) {
		return null;
	}

	@Override
	public List<Train> searchTrainsBySeatsAvailable(int seatsAvailable) {
		return null;
	}

	@Override
	public List<Train> getAllTrains() {
		return null;
	}

}
