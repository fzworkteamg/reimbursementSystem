package cn.reimbursement.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ServiceAspect {
	
	private Logger logger = Logger.getLogger(ServiceAspect.class);
	
	@Pointcut("execution(public * cn.reimbursement.service.*.*(..))")
	public void servicePosition(){}
	
	@Before("servicePosition()")
    public void exBefore(JoinPoint joinPoint){
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        logger.info("url:"+request.getRequestURL());
        logger.info("method:"+request.getMethod());
        logger.info("class method:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        logger.info("args:"+joinPoint.getArgs().toString());
    }
    
    @After("servicePosition()")
    public void exAfter(JoinPoint joinPoint){
        logger.info("class method:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()
                +"方法执行完毕！");
    }
    
    @AfterReturning(returning="result",pointcut="servicePosition()")
    public void exAfterReturning(Object result){
        logger.info("执行返回值："+result);
    }
}
