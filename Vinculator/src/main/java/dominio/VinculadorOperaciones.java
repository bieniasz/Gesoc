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
	 RepositorioIngresos repositorioIngresos;
	 RepositorioEgresos repositorioEgresos;
	 RepositorioCentral repositorioCentral;
	
	
	 final Gson gsonEgresos = new Gson();
	 
	 
	 public String vincular(Request request, Response response) {
		 String Ingresos = request.body();
		 String Egresos = request.body();

	     this.repositorioIngresos = gsonEgresos.fromJson(Ingresos, RepositorioIngresos.class);	
	     this.repositorioEgresos = gsonEgresos.fromJson(Egresos, RepositorioEgresos.class);				 
	     

	     return this.criterio.ejecutar(repositorioIngresos,repositorioEgresos);
	 
	 }
	 

	  
}
