package id.nullpointr;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PreHandlerControllerAspect {
	 private static final Logger logger = LoggerFactory.getLogger(PreHandlerControllerAspect.class);

	 @Before("execution(* id.nullpointr.controller.web.controller.MainController.*(..))") 
	 public void beforeController(JoinPoint joinPoint){
		 logger.debug("Before calling main controller. Only for testing purpose ...");
	 }
}
