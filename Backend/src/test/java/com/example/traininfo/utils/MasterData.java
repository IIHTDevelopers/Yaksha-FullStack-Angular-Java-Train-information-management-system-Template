package com.example.traininfo.utils;

import com.example.traininfo.dto.TrainDTO;
import com.example.traininfo.entity.Train;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MasterData {


    public static List<Train> getTrainList(){
        List<Train> trains = new ArrayList<>();

        Train train = new Train();
        train.setId(1L);
        train.setName("ranakpur");
        train.setArrivalStation("pali");
        train.setDistance(10);
        train.setDuration(10);
        train.setFare(10D);
        train.setDepartureStation("pali");
        train.setNumber(12345);
        train.setSeatsAvailable(1);
        trains.add(train);

        train = new Train();
        train.setId(2L);
        train.setName("suryanagari");
        train.setArrivalStation("pali");
        train.setDistance(10);
        train.setDuration(10);
        train.setFare(10D);
        train.setDepartureStation("pali");
        train.setNumber(56789);
        train.setSeatsAvailable(1);
        trains.add(train);
        return  trains;
    }

    public static Train getTrain(){
        Train train = new Train();
        train.setId(1L);
        train.setName("ranakpur");
        train.setArrivalStation("pali");
        train.setDistance(10);
        train.setDuration(10);
        train.setFare(10D);
        train.setDepartureStation("pali");
        train.setNumber(12345);
        train.setSeatsAvailable(1);

        return train;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String randomStringWithSize(int size) {
        String s = "";
        for (int i = 0; i < size; i++) {
            s+= "A";
        }
        return s;
    }
}
