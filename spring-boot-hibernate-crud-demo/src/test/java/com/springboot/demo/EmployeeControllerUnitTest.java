package com.springboot.demo;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.demo.service.EmployeeService;
import com.springboot.demo.web.EmployeeController;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService service;

    

    @Test
    public void getAllEmployeesTest() throws Exception {
        mockMvc.perform(get("/employees/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
        System.out.println("Test Passed");

        verify(service, times(1)).getAllEmployees();
        
    }

	/*
	 * @Test public void getAllEmployeesByIdTest() throws Exception {
	 * mockMvc.perform(get("/employees/{id}")) .andExpect(status().isOk())
	 * .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	 * .andExpect(content().json("[]"));
	 * 
	 * verify(service, times(1)).getEmployeeById(long id); }
	 */
}
