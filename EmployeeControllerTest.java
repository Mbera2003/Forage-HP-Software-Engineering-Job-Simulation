package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = new Employee();
        employee.setEmployee_id("001");
        employee.setFirst_name("John");
        employee.setLast_name("Doe");
        employee.setEmail("john.doe@example.com");
        employee.setTitle("Software Engineer");
    }

    @Test
    public void testAddEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employee_id\": \"001\", \"first_name\": \"John\", \"last_name\": \"Doe\", \"email\": \"john.doe@example.com\", \"title\": \"Software Engineer\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void testAddInvalidEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employee_id\": \"\", \"first_name\": \"\", \"last_name\": \"\", \"email\": \"\", \"title\": \"\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
