package com.example.traininfo.exception;

import com.example.traininfo.controller.TrainController;
import com.example.traininfo.entity.Train;
import com.example.traininfo.service.TrainService;
import com.example.traininfo.utils.MasterData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.traininfo.utils.MasterData.getTrain;
import static com.example.traininfo.utils.TestUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(TrainController.class)
@AutoConfigureMockMvc
public class TrainExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TrainService trainService;

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testCreateTrainInvalidDataException() throws Exception {
        Train train = getTrain();
        train.setName(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/trains")
                .content(MasterData.asJsonString(train)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(),
                (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
                exceptionTestFile);

    }

    @Test
    public void testUpdateTrainInvalidDataException() throws Exception {
        Train train = getTrain();
        train.setName(null);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/trains/"+ train.getId())
                .content(MasterData.asJsonString(train)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(),
                (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
                exceptionTestFile);

    }

    @Test
    public void testGetTrainByIdResourceNotFoundException() throws Exception{
        Train train = getTrain();
        GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Train not found with id: " + train.getId());

        when(this.trainService.getTrainById(train.getId()))
                .thenThrow(new ResourceNotFoundException("Train not found with id: " + train.getId()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains/" + train.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
                exceptionTestFile);
    }

    @Test
    public void testSearchTrainsByNameResourceNotFoundException() throws Exception{
        Train train = getTrain();
        GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Train not found with name: " + train.getName());

        when(this.trainService.searchTrainsByName(train.getName()))
                .thenThrow(new ResourceNotFoundException("Train not found with name: " + train.getName()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains/name/" + train.getName())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
                exceptionTestFile);
    }


    @Test
    public void testUpdateTrainByIdResourceNotFoundException() throws Exception {
        Train train = getTrain();
        GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Train not found with id: " + train.getId());

        when(this.trainService.updateTrain(eq(train.getId()), any())).thenThrow(new ResourceNotFoundException("Train not found with id: " + train.getId()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/trains/" + train.getId())
                .content(MasterData.asJsonString(train))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
                exceptionTestFile);

    }

    @Test
    public void testDeleteTrainByIdResourceNotFoundException() throws Exception {
        Train train = getTrain();
        GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Train not found with id: " + train.getId());
        when(this.trainService.deleteTrain(train.getId()))
                .thenThrow(new ResourceNotFoundException("Train not found with id: " + train.getId()));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/trains/" + train.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
                exceptionTestFile);

    }
}
