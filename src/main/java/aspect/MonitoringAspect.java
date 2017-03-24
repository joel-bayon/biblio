package aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class MonitoringAspect {
	
	static Logger logger = Logger.getLogger("monitoring");
	
	@Around(value="execution(* dao.jpa"
			+ ".*.*(..))")
	public Object proceedingDuration(ProceedingJoinPoint joinPoint) throws Throwable {
		//traitement amont (before) ...
		long debut = System.currentTimeMillis();

		Object result = joinPoint.proceed();
		//traitement aval (after) ... 
		
		long fin = System.currentTimeMillis();
		logger.info(joinPoint.getSignature().toShortString() + " duration = " + (fin-debut) + " ms");
		
		return result;
		
	}

}
