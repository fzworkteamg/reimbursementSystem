package cn.reimbursement.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {
	
//	@Pointcut("execution(public * cn.reimbursement.service.*.*(..))")
//	public void position(){}
//	
//	 @AfterThrowing(throwing="ex",pointcut="position()")
//	    public void doAfterThrowing(Throwable ex) {
//	    		System.out.println("目标方法中抛出的异常:" + ex);
//	    		System.out.println("ex.getMessage():" + ex.getMessage());
//	    		
//	    		
//	    }
}
