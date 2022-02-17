package com.sg.emp.exceptions;

public class EmployeeExceptions extends RuntimeException{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Object data;


public EmployeeExceptions(String errorMsg) {
	super(errorMsg);
	
}

public EmployeeExceptions(String errorMsg, Object data) {
	super(errorMsg);
	this.data=data;
	
	
}


public Object getData() {
	return data;
}

public void setData(Object data) {
	this.data = data;
}

}
