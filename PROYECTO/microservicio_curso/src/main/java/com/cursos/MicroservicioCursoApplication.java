package com.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy //ya tiene activadas las capacidades de programación aspectual
@ComponentScan(basePackages = {"Controllers"}) // pueda leer fuera del paquete los controladores
public class MicroservicioCursoApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioCursoApplication.class, args);
	}

}
