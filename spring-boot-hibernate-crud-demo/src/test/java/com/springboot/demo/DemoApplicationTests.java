package com.springboot.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.demo.model.EmployeeEntity;
import com.springboot.demo.repository.EmployeeRepository;
import com.springboot.demo.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	
	@Autowired
	private EmployeeService service;
	
	@MockBean
	private EmployeeRepository repository;
	
	@Test
	public void getAllEmployeeTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new EmployeeEntity((long) 1, "Jayant", "Kumar", "Jayant@gmail.com" ), 
						new EmployeeEntity((long) 2, "Gaurav", "Kumar", "Gaurav@gmail.com"))
				.collect(Collectors.toList()));
		assertEquals(2, service.getAllEmployees().size());
	}
	
	@Test
	public void saveEmployeeTest() throws Exception  {
		EmployeeEntity employee = new EmployeeEntity((long) 1, "Jayant", "Kumar", "Jayant@gmail.com");
		when(repository.save(employee)).thenReturn(employee);
		assertEquals(employee, service.createOrUpdateEmployee(employee));
	}
	/*
	 * @Test public void getAllEmployeeByIdTest() { Long id = Long.valueOf(1);
	 * when(repository.findById(id)) .thenReturn(Stream.of(new
	 * EmployeeEntity((long)2,"Jayant", "Kumar",
	 * "Jayant@gmail.com")).collect(Collectors.toList())); assertEquals(1,
	 * service.getEmployeeById(id).size()); }
	 */


	

}
