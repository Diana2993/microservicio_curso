package Controllers;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//AOP
//Configuration
@Aspect
@Configuration
public class Aspecto2 {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//Esta anotación ejecuta un advice después de la ejecución del punto de corte especificado, siempre que el método del punto de corte retorne de forma correcta (sin generar errores).
	@AfterReturning(value = "execution(* com.in28minutes.springboot.tutorial.basics.example.aop.business.*.*(..))", 
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		logger.info("{} returned with value {}", joinPoint, result);
	}
	//Esta anotación ejecuta un advice tras la ejecución de un método, sin importar si fue por exception o por flujo normal (return)
	@After(value = "execution(* com.in28minutes.springboot.tutorial.basics.example.aop.business.*.*(..))")
	public void after(JoinPoint joinPoint) {
		logger.info("after execution of {}", joinPoint);
	}
	//Esta anotación ejecuta un advice antes de la ejecución del punto de corte especificado.
	@Before("execution(* com.in28minutes.springboot.tutorial.basics.example.aop.data.*.*(..))")
	public void before(JoinPoint joinPoint){
		//Advice
		logger.info(" Check for user access ");
		logger.info(" Allowed execution for {}", joinPoint);
	}
	
	@After("controllerMethodPointcut()")
	public void After(JoinPoint joinPoint)
	{
		System.out.println(joinPoint.getSignature().getName());
		System.out.println("================@After(controllerMethodPointcut())");
	}

	@org.aspectj.lang.annotation.Around("controllerMethodPointcut()")
	public Object Around(ProceedingJoinPoint joinPoint) throws Throwable
	{
		System.out.println(joinPoint.getSignature().getName());

		System.out.println("@Around(controllerMethodPointcut())");

		return joinPoint.proceed();
	}

	//esto controla el método pointcut
	@org.aspectj.lang.annotation.Around ("controllerMethodPointcut ()") // Especifique la regla del interceptor; también puede escribir directamente "ejecución (* com.zx .........)" aquí
	public Object Interceptor(ProceedingJoinPoint pjp){
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Signature method = null;
		String methodName = method .getName (); // Obtener el nombre del método interceptado

		// Establecer <Object> allParams = new LinkedHashSet <> (); // Guardar todos los parámetros de solicitud para la salida en el registro

		System.out.println("================@Around(controllerMethodPointcut())");

		Object result = null;

		Object[] args = pjp.getArgs();
		for(Object arg : args){
			System.out.println(arg);
		}

		// En circunstancias normales, continúe ejecutando el método interceptado
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return result;
	}
	
}