package Controllers;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Aspecto {
 
@Autowired
@Around("execution(* Controllers.*.*())")
public void tiempoPasado(ProceedingJoinPoint punto) throws Throwable {


Long tiempo1 = System.currentTimeMillis();
punto.proceed();
Long tiempo2 = System.currentTimeMillis();
Long total = tiempo2 - tiempo1;
	if (total >= 2000) {
	System.out.format("el metodo es : x y el tiempo transcurrido x", punto.getSignature().getName(), total); 
	}
	
}
}
