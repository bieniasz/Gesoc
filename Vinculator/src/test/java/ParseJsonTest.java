package test.java;

import com.google.gson.*;

import com.google.gson.stream.JsonReader;

import main.java.dominio.Egreso;
import main.java.dominio.Ingreso;
import main.java.dominio.RepositorioCentral;

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

public class ParseJsonTest {
	 
	private static final Gson gsonCentral = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
         @Override
         public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
             return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
         }
     }).create();
     
	 private   RepositorioCentral repositorioCentral;
	
	@Test
    public void lala() throws Exception{
       
		// caargo el modelo de json  guardado en resources
		URL res = getClass().getClassLoader().getResource("ejemplo_repositorio_central.json");
		File file = Paths.get(res.toURI()).toFile();
		String filename = file.getAbsolutePath();
		System.out.println(filename);
	      
        try {
    		
        	 BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        
         this.repositorioCentral = gsonCentral.fromJson(bufferedReader, RepositorioCentral.class);
        
        
	    } catch (IOException e) {
		       e.printStackTrace(); 
		       
		   		}
        
        
       
        for(  Ingreso ingreso   : repositorioCentral.getRepositorioIngresos().getIngresos()
			      
			      ) {
			    	
        				System.out.println(ingreso.getDescripcion()); 	
        				
				}	
        
        for(  Egreso egreso   : repositorioCentral.getRepositorioEgresos().getEgresos()
			      
			      ) {
			    	
        					System.out.println(egreso.getDetalle()); 
        					System.out.println(egreso.getAsignado());
				}	
        
	
	}
    
	
}
