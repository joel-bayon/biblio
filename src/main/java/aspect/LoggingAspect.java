package aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;


@Component
public class LoggingAspect {
	
	public void logBefore(JoinPoint joinpoint) {
		Logger logger = Logger.getLogger(joinpoint.getTarget().getClass());
		if(logger.isInfoEnabled())
			logger.info(joinpoint.getSignature().toLongString());
		
	}
	
	public void logAfter(JoinPoint joinpoint, Object result) {
		Logger logger = Logger.getLogger(joinpoint.getTarget().getClass());
		if(logger.isInfoEnabled())
			logger.info(joinpoint.getSignature().toShortString() + " returns : " + result);
		
	}

}
