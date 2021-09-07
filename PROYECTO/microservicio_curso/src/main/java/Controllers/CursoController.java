package Controllers;


import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import com.cursos.model.Curso;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
@Aspect
@RestController // siempre hay que poner esta etiqueta en los controladores
public class CursoController {
	
	private List<Curso> cursos;
	@PostConstruct // cuando se instancie este controler se ejectará el siguiente método
	public void init() {
		cursos=new ArrayList<>();
		cursos.add(new Curso("Spring",25,"tarde"));
		cursos.add(new Curso("Spring boot",20,"tarde"));
		cursos.add(new Curso("Python",30,"tarde"));
		cursos.add(new Curso("Java EE",50,"fin de semana"));
		cursos.add(new Curso("Java básico",30,"mañana"));
	}
	
	@GetMapping(value="cursos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> getCursos(){
		return cursos;
	}
		
	@GetMapping(value="curso",produces = MediaType.APPLICATION_JSON_VALUE)
	//para que ueda leer la ruta-valor/tipo ( El cliente quiere formato json )
	public Curso getCurso() {
	return new Curso("java",100,"mañana");
	}
		
	@GetMapping(value="cursos/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> buscarCursos(@PathVariable("name") String nombre){
		List<Curso> aux=new ArrayList<>();
		for(Curso c:cursos) {
			if(c.getNombre().contains(nombre)) {
				aux.add(c);
			}
		}
		return aux;
	}
}