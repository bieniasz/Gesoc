package main.java.dominio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import spark.Spark;



public  class  VinculadorOperaciones  {

	 CriterioEjecucion criterio;
	 List <Condicion> condiciones;
	 RepositorioIngresos repositorioIngresos;
	 RepositorioEgresos repositorioEgresos;
	 RepositorioCentral repositorioCentral;
	 List <IngresoVinculado> vinculados; 
	
	 final Gson gsonEgresos = new Gson();
	 
	 public static void main(String[] args) {
			
	
			
			
	}

	 
	 
	 public String vincular(String Egresos, String Ingresos) {
				 
		 
		 
		
	     
	     this.repositorioIngresos = gsonEgresos.fromJson(Ingresos, RepositorioIngresos.class);	
	     this.repositorioEgresos = gsonEgresos.fromJson(Egresos, RepositorioEgresos.class);				 
	     

	     
	  
	      
	      
	      
	     return this.criterio.ejecutar(repositorioIngresos,repositorioEgresos);
	 
	 }
	 
	 
	 
	  
}
