package com.sg.emp.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sg.emp.constants.EmployeeConstants;
import com.sg.emp.dto.EmployeeDTO;
import com.sg.emp.dto.WSResponseStatus;
import com.sg.emp.exceptions.EmployeeExceptions;
import com.sg.emp.service.EmployeeService;
import com.sg.emp.validator.EmployeeValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/emp")
public class EmployeeController {
	private static final Logger logger = LogManager.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService empService;

	@ApiOperation("register employee data")
	@ApiResponses(value= {
			@ApiResponse(code =200 , message="employee registered successfully"),
			@ApiResponse(code =404 , message="page not found"),
			@ApiResponse(code =500 , message="internal server error"),
			@ApiResponse(code =400 , message="application error")
	})
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<WSResponseStatus> empRegister( @RequestBody EmployeeDTO empDTO)
			throws EmployeeExceptions {
		WSResponseStatus wsResponseStatus = null;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		try {

			StringBuffer validateEmpSB = EmployeeValidator.Validate(empDTO);
			logger.info("validation of employee completed");

			if (validateEmpSB.length() > 0) {
				logger.error("employee entered data is not valid");

				throw new Exception(validateEmpSB.toString());
			}
			logger.info("validation of employee completed");

			int status = empService.registerEmployee(empDTO);
			logger.info("employee registry status" +status);
			if(status > 0)
			wsResponseStatus = new WSResponseStatus(); 
			wsResponseStatus.setStatusMessage("Employee registered successfully");
			wsResponseStatus.setStatus(EmployeeConstants.SUCCESS);
			wsResponseStatus.setStatusCode(EmployeeConstants.SUCCESS_CODE);
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.OK);
		} catch (EmployeeExceptions e) {
			logger.error(EmployeeConstants.ERROR_OCCURED + e);
			wsResponseStatus = new WSResponseStatus();
			wsResponseStatus.setStatus(EmployeeConstants.FAILURE);
			wsResponseStatus.setStatusCode(EmployeeConstants.ERR_CODE_BAD_REQUEST);
			wsResponseStatus.setStatusMessage(e.getMessage());
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(EmployeeConstants.ERROR_OCCURED + e);
			wsResponseStatus = new WSResponseStatus();
			wsResponseStatus.setStatus(EmployeeConstants.FAILURE);
			wsResponseStatus.setStatusCode(EmployeeConstants.ERR_CODE_INT_SERVER_ERR);
			wsResponseStatus.setStatusMessage(e.getMessage());
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@ApiOperation("fetching employee data")
	@ApiResponses(value= {
			@ApiResponse(code =200 , message="employee data fetched successfully"),
			@ApiResponse(code =404 , message="page not found"),
			@ApiResponse(code =500 , message="internal server error"),
			@ApiResponse(code =400 , message="application error")
	})
	@GetMapping(value = "/fetch/{employeename}",  produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<WSResponseStatus> empFetch(@PathVariable(name="employeename")String employeename)
			throws EmployeeExceptions {
		WSResponseStatus wsResponseStatus = null;
		List<Map<String,Object>> empList = null;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		try {
			logger.info("requested employee details for empname "+employeename);


			if(null!=employeename || ! employeename.isEmpty())
			empList = empService.fetchEmployee(employeename);
			logger.info("employee details for empname found in db"+empList.size());

			wsResponseStatus = new WSResponseStatus(); 
			wsResponseStatus.setStatusMessage("Employee data fetched successfully");
			wsResponseStatus.setStatus(EmployeeConstants.SUCCESS);
			wsResponseStatus.setStatusCode(EmployeeConstants.SUCCESS_CODE);
			wsResponseStatus.setData(empList);
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.OK);
		} catch (EmployeeExceptions e) {
			logger.error(EmployeeConstants.ERROR_OCCURED + e);
			wsResponseStatus = new WSResponseStatus();
			wsResponseStatus.setStatus(EmployeeConstants.FAILURE);
			wsResponseStatus.setStatusCode(EmployeeConstants.ERR_CODE_BAD_REQUEST);
			wsResponseStatus.setStatusMessage(e.getMessage());
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(EmployeeConstants.ERROR_OCCURED + e);
			wsResponseStatus = new WSResponseStatus();
			wsResponseStatus.setStatus(EmployeeConstants.FAILURE);
			wsResponseStatus.setStatusCode(EmployeeConstants.ERR_CODE_INT_SERVER_ERR);
			wsResponseStatus.setStatusMessage(e.getMessage());
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(value = "/fetch",  produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<WSResponseStatus> empFetchByOrder()
			throws EmployeeExceptions {
		WSResponseStatus wsResponseStatus = null;
		List<Map<String,Object>> empList = null;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		try {
			logger.info("requested employee details ");


			
			empList = empService.fetchEmployeeByOrder();
			logger.info("employee details  found in db"+empList.size());

			wsResponseStatus = new WSResponseStatus(); 
			wsResponseStatus.setStatusMessage("Employee data fetched successfully");
			wsResponseStatus.setStatus(EmployeeConstants.SUCCESS);
			wsResponseStatus.setStatusCode(EmployeeConstants.SUCCESS_CODE);
			wsResponseStatus.setData(empList);
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.OK);
		} catch (EmployeeExceptions e) {
			logger.error(EmployeeConstants.ERROR_OCCURED + e);
			wsResponseStatus = new WSResponseStatus();
			wsResponseStatus.setStatus(EmployeeConstants.FAILURE);
			wsResponseStatus.setStatusCode(EmployeeConstants.ERR_CODE_BAD_REQUEST);
			wsResponseStatus.setStatusMessage(e.getMessage());
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error(EmployeeConstants.ERROR_OCCURED + e);
			wsResponseStatus = new WSResponseStatus();
			wsResponseStatus.setStatus(EmployeeConstants.FAILURE);
			wsResponseStatus.setStatusCode(EmployeeConstants.ERR_CODE_INT_SERVER_ERR);
			wsResponseStatus.setStatusMessage(e.getMessage());
			return new ResponseEntity<WSResponseStatus>(wsResponseStatus, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
