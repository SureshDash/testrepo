package com.sg.emp.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sg.emp.dao.IEmployeDao;
import com.sg.emp.dto.EmployeeDTO;

@Repository
public class EmployeeDaoImpl implements IEmployeDao {
	private static final Logger logger = LogManager.getLogger(EmployeeDaoImpl.class);

	@Autowired
	@Qualifier("pgJdbcTemplate")
	JdbcTemplate pgJdbcTemplate;

	@Override
	public Integer saveEmployeeData(EmployeeDTO empDTO) {
		int status=0;
		try {
		 status = pgJdbcTemplate.update(
				"insert into employee (firstname,lastname,gender,DOB,department) values (?,?,?,?,?)",
				empDTO.getFirstName(), empDTO.getLastName(), empDTO.getGender(), empDTO.getDateOfBirth(),
				empDTO.getDepartment());
		 logger.info("employee registry status "+status);
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return status;
	}

	@Override
	public List<Map<String,Object>> fetchchEmployee(String employeename) {
		String sqlQuery="select firstname,lastname,gender,dob,department from employee where firstname like '"+employeename+"'";
		List<Map<String,Object>> empList= pgJdbcTemplate.queryForList(sqlQuery);
		 logger.info("retrived employee list size "+empList.size());

		return empList;
		
		
	}

	@Override
	public List<Map<String, Object>> fetchchEmployeeByOrder() {
		String sqlQuery="select firstname,lastname,gender,dob,department from employee  order by firstname asc";
		List<Map<String,Object>> empList= pgJdbcTemplate.queryForList(sqlQuery);
		 logger.info("retrived employee list size "+empList.size());

		return empList;
	}

}
