package com.example.traininfo.functional;

import com.example.traininfo.controller.TrainController;
import com.example.traininfo.entity.Train;
import com.example.traininfo.service.TrainService;
import com.example.traininfo.utils.MasterData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.example.traininfo.utils.MasterData.*;
import static com.example.traininfo.utils.TestUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(TrainController.class)
@AutoConfigureMockMvc
public class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainService trainService;

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testCreateTrain() throws Exception {
        Train savedTrain = getTrain();
        when(trainService.createTrain(any())).thenReturn(savedTrain);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/trains")
                .content(MasterData.asJsonString(savedTrain))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedTrain))
                        ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testCreateTrainIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        Train savedTrain = getTrain();
        when(this.trainService.createTrain(any())).then(new Answer<Train>() {

            @Override
            public Train answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return savedTrain;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/trains")
                .content(MasterData.asJsonString(savedTrain)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
    }
    @Test
    public void testGetTrainById() throws Exception {
        Train train = getTrain();
        when(this.trainService.getTrainById(train.getId())).thenReturn(train);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains/" + train.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(train)) ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testGetTrainByIdIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        Train savedTrain = getTrain();
        when(this.trainService.getTrainById(savedTrain.getId())).then(new Answer<Train>() {

            @Override
            public Train answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return savedTrain;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains/" + savedTrain.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
    }

    @Test
    public void testGetAllTrains() throws Exception {
        List<Train> trainList = getTrainList();

        when(this.trainService.getAllTrains()).thenReturn(trainList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(trainList)) ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testGetAllTrainsIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        List<Train> trainList = getTrainList();
        when(this.trainService.getAllTrains()).then(new Answer<List<Train>>() {

            @Override
            public  List<Train> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return trainList;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains/")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
    }

    @Test
    public void testUpdateTrain() throws Exception {
        Train updateTrain = getTrain();

        when(this.trainService.updateTrain(eq(updateTrain.getId()), any())).thenReturn(updateTrain);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/trains/" + updateTrain.getId())
                .content(MasterData.asJsonString(updateTrain)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(updateTrain))
                        ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testUpdateTrainIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        Train updateTrain = getTrain();

        when(this.trainService.updateTrain(eq(updateTrain.getId()), any())).then(new Answer<Train>() {

            @Override
            public Train answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return updateTrain;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/trains/" + updateTrain.getId())
                .content(MasterData.asJsonString(updateTrain)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testDeleteTrain() throws Exception {
        Train train = getTrain();
        when(this.trainService.deleteTrain(train.getId())).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/trains/" + train.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals("") ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testDeleteTrainIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        Train train = getTrain();
        when(this.trainService.deleteTrain(train.getId())).then(new Answer<Boolean>() {

            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return true;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/trains/" + train.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testGetTrainByNumber() throws Exception {
        Train train = getTrain();
        when(this.trainService.getTrainByNumber(train.getNumber())).thenReturn(train);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains/number/" + train.getNumber())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(train)) ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetTrainByNumberIsServiceMethodCalled() throws Exception{
        final int count[] = new int[1];
        Train train = getTrain();

        when(this.trainService.getTrainByNumber(train.getNumber())).then(new Answer<Train>() {

            @Override
            public Train answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return train;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains/number/" + train.getNumber())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
    }

    @Test
    public void testSearchTrainsByName() throws Exception {
        List<Train> trainList = getTrainList();
        when(this.trainService.searchTrainsByName(trainList.get(0).getName())).thenReturn(trainList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains/name/" + trainList.get(0).getName())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(trainList)) ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testSearchTrainsByNameIsServiceMethodCalled() throws Exception{
        final int count[] = new int[1];
        List<Train> trainList = getTrainList();

        when(trainService.searchTrainsByName(trainList.get(0).getName())).then(new Answer<List<Train>>() {

            @Override
            public List<Train> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return trainList;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains/number/" + trainService.searchTrainsByName(trainList.get(0).getName()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
    }

    @Test
    public void testSearchTrainsBySeatsAvailable() throws Exception {
        List<Train> trainList = getTrainList();
        when(this.trainService.searchTrainsBySeatsAvailable(trainList.get(0).getSeatsAvailable())).thenReturn(trainList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains/seats-available/" + trainList.get(0).getSeatsAvailable())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(trainList)) ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testSearchTrainsBySeatsAvailableIsServiceMethodCalled() throws Exception{
        final int count[] = new int[1];
        List<Train> trainList = getTrainList();

        when(trainService.searchTrainsBySeatsAvailable(trainList.get(0).getSeatsAvailable())).then(new Answer<List<Train>>() {

            @Override
            public List<Train> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return trainList;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/trains/seats-available/" + trainService.searchTrainsBySeatsAvailable(trainList.get(0).getSeatsAvailable()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
    }
}
