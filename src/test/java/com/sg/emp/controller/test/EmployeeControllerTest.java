
package com.sg.emp.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sg.emp.controller.EmployeeController;
import com.sg.emp.dao.IEmployeDao;
import com.sg.emp.dto.EmployeeDTO;
import com.sg.emp.service.EmployeeService;

public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController empController;

	@Mock
	IEmployeDao dao;

	@Mock
	EmployeeService service;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void employeeRegisterTest() {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("Suresh");
		employeeDTO.setLastName("Dash");
		employeeDTO.setGender("male");
		employeeDTO.setDateOfBirth(new Date());
		employeeDTO.setDepartment("software");
		empController.empRegister(employeeDTO);
		verify(service, times(1)).registerEmployee(employeeDTO);
	}

	@Test
	public void employeeFetchTest() {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("suresh");
		employeeDTO.setLastName("Dash");
		employeeDTO.setGender("male");
		employeeDTO.setDateOfBirth(new Date());
		employeeDTO.setDepartment("software");

		EmployeeDTO employeeDTO1 = new EmployeeDTO();
		employeeDTO1.setFirstName("suresh");
		employeeDTO1.setLastName("Dash");
		employeeDTO1.setGender("male");
		employeeDTO1.setDateOfBirth(new Date());
		employeeDTO1.setDepartment("software");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("emp1", employeeDTO);
		map.put("emp2", employeeDTO1);
		list.add(map);
		when(service.fetchEmployee("suresh")).thenReturn(list);
		/*
		 * List<Map<String, Object>> empList = (List<Map<String, Object>>)
		 * empController.empFetch("suresh"); assertEquals(2, list.size());
		 */
		//verify(service, times(1)).fetchEmployee("suresh");
	}
}
