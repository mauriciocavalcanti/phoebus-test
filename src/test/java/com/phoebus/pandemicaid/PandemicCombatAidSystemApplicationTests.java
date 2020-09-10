package com.phoebus.pandemicaid;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
@TestMethodOrder(OrderAnnotation.class)
class PandemicCombatAidSystemApplicationTests {

  private static boolean isInitialized;
  private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
  private static final String FULLNESS = "{\"percentage\": 92}";
  private static final String HOSPITAL_A =
      "{ \"address\": { \"cep\": \"string\", \"city\": \"string\", \"latitude\": 0, \"longitude\": 0, \"neighborhood\": \"string\", \"number\": 0, \"state\": \"string\", \"street\": \"string\" }, \"cnpj\": \"string\", \"currentFullness\": 95, \"name\": \"hospital A\", \"resources\": [ { \"name\": \"médico\" },{ \"name\": \"médico\" },{ \"name\": \"médico\" },{ \"name\": \"médico\" },{ \"name\": \"médico\" } ]}";
  private static final String HOSPITAL_B =
      "{ \"address\": { \"cep\": \"string\", \"city\": \"string\", \"latitude\": 0, \"longitude\": 0, \"neighborhood\": \"string\", \"number\": 0, \"state\": \"string\", \"street\": \"string\" }, \"cnpj\": \"string\", \"currentFullness\": 55, \"name\": \"hospital 2\", \"resources\": [ { \"name\": \"enfermeiro\" },{ \"name\": \"enfermeiro\" },{ \"name\": \"tomógrafo\" },{ \"name\": \"respirador\" },{ \"name\": \"ambulância\" } ]}";
  private static final String EXCHANGE =
      "{ \"hospitalA\": { \"id\": 1, \"resources\": [ { \"id\": 1,\"weight\": 3 },{ \"id\": 2,\"weight\": 3 } ] }, \"hospitalB\": { \"id\": 2, \"resources\": [ { \"id\": 6,\"weight\": 3 },{ \"id\": 7,\"weight\": 3 } ] }}";

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @BeforeEach
  private void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    if (!isInitialized) {
      isInitialized = true;
    }
  }

  @Test
  @Order(1)
  public void createHospital() throws Exception {
    mockMvc.perform(post("/hospitals").contentType(CONTENT_TYPE).content(HOSPITAL_A))
        .andExpect(status().isCreated());
    mockMvc.perform(post("/hospitals").contentType(CONTENT_TYPE).content(HOSPITAL_B))
        .andExpect(status().isCreated());
  }

  @Test
  @Order(2)
  public void getHospitals() throws Exception {
    mockMvc.perform(get("/hospitals")).andExpect(status().isOk());
  }

  @Test
  @Order(3)
  public void postFullness() throws Exception {
    mockMvc.perform(post("/hospitals/1/fullnesses").contentType(CONTENT_TYPE).content(FULLNESS))
        .andExpect(status().isCreated());
  }

  @Test
  @Order(4)
  public void getFullnesses() throws Exception {
    mockMvc.perform(get("/hospitals/1/fullnesses")).andExpect(status().isOk());
  }

  @Test
  @Order(5)
  public void postExchange() throws Exception {
    mockMvc.perform(post("/exchanges").contentType(CONTENT_TYPE).content(EXCHANGE))
        .andExpect(status().isCreated());
  }
  
  @Test
  @Order(6)
  public void getReports() throws Exception {
    mockMvc.perform(get("/reports?id=1&id=2&id=3&id=4&id=5&id=6")).andExpect(status().isOk());
  }


}
