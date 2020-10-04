package main.java.dominio;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import main.java.criterios.CriterioEjecucionFecha;
import spark.Request;
import spark.Response;
import spark.Spark;



public  class  VinculadorOperaciones  {

	 CriterioEjecucion criterio;
	 RepositorioIngresos repositorioIngresos;
	 RepositorioEgresos repositorioEgresos;
	 RepositorioCentral repositorioCentral;
	
	 // preparo un builder para aceptar localdate
	 private static final Gson gsonCentral = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
         @Override
         public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
             return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
         }
     }).create();
     
	 
	 
	 
	 public String vincular(Request request, Response response) {
		 String requestBody=request.body();

		 this.criterio= new CriterioEjecucionFecha();	 
		 
		 this.repositorioCentral = gsonCentral.fromJson(requestBody, RepositorioCentral.class);
		
		 this.repositorioEgresos=this.repositorioCentral.repositorioEgresos;
		 this.repositorioIngresos=this.repositorioCentral.repositorioIngresos;
	     

	    return this.criterio.ejecutar(repositorioIngresos,repositorioEgresos);
	// return "ha sido vinculado";
	 }
	 

	  
}
