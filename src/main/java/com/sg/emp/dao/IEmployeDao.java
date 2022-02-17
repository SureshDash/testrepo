package com.sg.emp.dao;

import java.util.List;
import java.util.Map;

import com.sg.emp.dto.EmployeeDTO;

public interface IEmployeDao {
	
	Integer saveEmployeeData(EmployeeDTO empDTO);

	List<Map<String,Object>> fetchchEmployee(String employeename);

	List<Map<String, Object>> fetchchEmployeeByOrder();
	
}
