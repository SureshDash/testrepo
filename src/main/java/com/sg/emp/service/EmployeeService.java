package com.sg.emp.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sg.emp.dao.IEmployeDao;
import com.sg.emp.dto.EmployeeDTO;

@Service
public class EmployeeService {
	private static final Logger logger = LogManager.getLogger(EmployeeService.class);

@Autowired
IEmployeDao iEmpDao;

	public Integer registerEmployee(EmployeeDTO empDTO) {
		int status = 0;
		try {
		 status =iEmpDao.saveEmployeeData(empDTO);
		 System.out.println(status);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return status;
	}
	
	@Cacheable("employee")
	public List<Map<String,Object>> fetchEmployee(String employeename) {
		return iEmpDao.fetchchEmployee(employeename);
	}

	public List<Map<String, Object>> fetchEmployeeByOrder() {
		// TODO Auto-generated method stub
		return iEmpDao.fetchchEmployeeByOrder();
	}

	
}
