package cn.reimbursement.exceptionHandler;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = Exception.class)
	public String jsonErrorHandler(Exception e) throws Exception {
		logger.error(e.toString());
		return "error";
	}
}
