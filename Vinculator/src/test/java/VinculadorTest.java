package test.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
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
	 public void  vincular() throws Exception {
				// caargo el modelo de json  guardado en resources
				URL res = getClass().getClassLoader().getResource("ejemplo_repositorio_central.json");
				File file = Paths.get(res.toURI()).toFile();
				String filename = file.getAbsolutePath();
				System.out.println(filename);
	       
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