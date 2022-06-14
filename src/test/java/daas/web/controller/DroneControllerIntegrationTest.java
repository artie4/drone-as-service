package daas.web.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import daas.entity.Drone;
import daas.model.State;
import daas.web.request.DroneDto;
import daas.web.request.MedicationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class DroneControllerIntegrationTest {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        om.setDateFormat(simpleDateFormat);
    }

    @Test
    public void getAvailableDrones() throws Exception {

        List<DroneDto> actualRecords = om.readValue(mockMvc.perform(get("/api/v1/drones"))
                .andDo(print())
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(8)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<List<DroneDto>>() {
        });
    }

    @Test
    public void getMedicationByDroneNumber() throws Exception {

        MedicationDto actualRecord = om.readValue(mockMvc.perform(get("/api/v1/drones/45cea21a-2304-4983-b777-a817d61f1cdc/medication"))
                .andDo(print())
                .andExpect(jsonPath("$.name", is("Pills-2_100")))
                .andExpect(jsonPath("$.code", is("PILLS_2_100")))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<MedicationDto>() {
        });
    }

    @Test
    public void loadMedication() throws Exception {

        MedicationDto pills_2020_1 = new MedicationDto()
                .setName("Pills-2020-1")
                .setCode("PILLS_2020_1")
                .setWeight(380)
                .setImage(new byte[16184]);

        DroneDto actualRecord = om.readValue(mockMvc.perform(post("/api/v1/drones/f02787fe-3b89-405d-9628-e75a2f29c9a5/medication")
                .contentType("application/json")
                .content(om.writeValueAsString(pills_2020_1)))
                .andDo(print())
                .andExpect(jsonPath("$.state", is(State.LOADING.toString())))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), new TypeReference<>() {
        });
    }
}