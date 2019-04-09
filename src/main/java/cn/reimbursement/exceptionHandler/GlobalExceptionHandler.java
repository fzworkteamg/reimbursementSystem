package cn.reimbursement.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public String jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		return "error";
	}
}
