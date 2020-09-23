package main.java.dominio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;



public abstract class  VinculadorOperaciones  {

	 CriterioEjecucion criterio;
	 List <Condicion> condiciones;
	 RepositorioIngresos repositorioIngresos;
	 RepositorioEgresos repositorioEgresos;
	 List <IngresoVinculado> vinculados; 
	 
	 public String vincular(String Egresos, String Ingresos, String fecha ) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		 
		 LocalDate fecha_hasta=LocalDate.parse(fecha);
		 final Gson gsonEgresos = new Gson();
				 
		
	     
	     this.repositorioIngresos = gsonEgresos.fromJson(Ingresos, RepositorioIngresos.class);	
	     this.repositorioEgresos = gsonEgresos.fromJson(Egresos, RepositorioEgresos.class);				 
	     

	     
	  
	      
	      
	      
	     return this.criterio.ejecutar(repositorioIngresos,repositorioEgresos);
	 
	 }
	 
	 
	 
	  
}
