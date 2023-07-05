package com.example.traininfo.boundary;

import com.example.traininfo.entity.Train;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static com.example.traininfo.utils.MasterData.getTrain;
import static com.example.traininfo.utils.MasterData.randomStringWithSize;
import static com.example.traininfo.utils.TestUtils.*;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testNumberNotNull() throws Exception {
        Train train = getTrain();
        train.setNumber(null);
        Set<ConstraintViolation<Train>> violations = validator.validate(train);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameNotNull() throws Exception {
        Train train = getTrain();
        train.setName(null);
        Set<ConstraintViolation<Train>> violations = validator.validate(train);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameMinThree() throws Exception {
        Train train = getTrain();
        train.setName(randomStringWithSize(1));
        Set<ConstraintViolation<Train>> violations = validator.validate(train);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameMaxHundred() throws Exception {
        Train train = getTrain();
        train.setName(randomStringWithSize(101));
        Set<ConstraintViolation<Train>> violations = validator.validate(train);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testDepartureStationNotNull() throws Exception {
        Train train = getTrain();
        train.setDepartureStation(null);
        Set<ConstraintViolation<Train>> violations = validator.validate(train);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testArrivalStationNotNull() throws Exception {
        Train train = getTrain();
        train.setArrivalStation(null);
        Set<ConstraintViolation<Train>> violations = validator.validate(train);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testDurationNotNull() throws Exception {
        Train train = getTrain();
        train.setDuration(null);
        Set<ConstraintViolation<Train>> violations = validator.validate(train);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testDistanceNotNull() throws Exception {
        Train train = getTrain();
        train.setDistance(null);
        Set<ConstraintViolation<Train>> violations = validator.validate(train);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

}
