package test.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import main.java.criterios.CriterioEjecucionFecha;
import main.java.criterios.OrdenValorPrimeroEgreso;
import main.java.dominio.CriterioEjecucion;
import main.java.dominio.RepositorioCentral;
import main.java.dominio.RepositorioEgresos;
import main.java.dominio.RepositorioIngresos;
import spark.Request;
import spark.Response;

public class VinculadorTest {

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
     
	 
	 
	 
	@Test
	 public void  vincular() {
		  String filename="C:\\Users\\Abdul\\ejemplo_repositorio_central.json";
	       
		  this.criterio= new OrdenValorPrimeroEgreso();	
		  
	        try {
	    		
	        	 BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
	        
	         repositorioCentral = gsonCentral.fromJson(bufferedReader, RepositorioCentral.class);
	         this.repositorioEgresos=this.repositorioCentral.repositorioEgresos;
			 this.repositorioIngresos=this.repositorioCentral.repositorioIngresos;
	        
		    } catch (IOException e) {
			       e.printStackTrace(); 
			  
			   		}
	        System.out.println(this.criterio.ejecutar(repositorioIngresos,repositorioEgresos));  
		    
	    
		

	 

	 }
}