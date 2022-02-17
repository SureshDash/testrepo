package com.sg.emp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sg.emp.dto.EmployeeDTO;

/**
 * @author Suresh Dash
 *
 */
public class EmployeeValidator {

	public static StringBuffer Validate(EmployeeDTO empDTO) {
		StringBuffer empSB = new StringBuffer();
		if (empDTO.getFirstName().isEmpty() || null == empDTO.getFirstName()) {
			empSB.append("First Name is Blank, Please enter valid First Name")
					.append(System.getProperty("line.separator"));
		}
		if (empDTO.getLastName().isEmpty() || null == empDTO.getLastName()) {
			empSB.append("Last Name is Blank, Please enter valid Last Name")
					.append(System.getProperty("line.separator"));
		}
		if (empDTO.getGender().isEmpty() || null == empDTO.getGender()) {
			empSB.append("Gender is Blank, Please enter valid Gender").append(System.getProperty("line.separator"));
		}
		if (null == empDTO.getDateOfBirth()) {
			empSB.append("Date of Birth is Blank, Please enter valid Date of Birth")
					.append(System.getProperty("line.separator"));
		} /*
			 * else { if (!isValidDate(String.valueOf(empDTO.getDateOfBirth()))) {
			 * empSB.append(" Please enter valid Date of Birth.Date field error")
			 * .append(System.getProperty("line.separator"));
			 * 
			 * } }
			 */
		if (empDTO.getDepartment().isEmpty() || null == empDTO.getDepartment()) {
			empSB.append("Department is Blank, Please enter valid department")
					.append(System.getProperty("line.separator"));
		}

		return empSB;
	}

	

}
