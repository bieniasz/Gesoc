package main.java.dominio;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Spark;



public  class  VinculadorOperaciones  {

	 CriterioEjecucion criterio;
	 List <Condicion> condiciones;
	 RepositorioIngresos repositorioIngresos;
	 RepositorioEgresos repositorioEgresos;
	 RepositorioCentral repositorioCentral;
	 List <IngresoVinculado> vinculados; 
	
	 final Gson gsonCentral = new Gson();
	 
		 
	 
	 public String vincular(Request request, Response response) {
				 
		 String requestBody=request.body();
		 
		 this.repositorioCentral = gsonCentral.fromJson(requestBody, RepositorioCentral.class);	
	     	      
	     return this.criterio.ejecutar(repositorioIngresos,repositorioEgresos);
	 
	 }
	 
	 
	 
	  
}
