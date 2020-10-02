package test.java;

import com.google.gson.*;

import com.google.gson.stream.JsonReader;

import main.java.dominio.Egreso;
import main.java.dominio.Ingreso;
import main.java.dominio.RepositorioCentral;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
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
    public void lala(){
       
       
       
        
        //String filename="C:\\Users\\Abdul\\ejemplo_repositorio_central.json";
       String filename="/Vinculator/resources/ejemplo_repositorio_central.json";
        try {
    		
        	 BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        
         this.repositorioCentral = gsonCentral.fromJson(bufferedReader, RepositorioCentral.class);
        
        
	    } catch (IOException e) {
		       e.printStackTrace(); 
		       
		   		}
        //System.out.println(repositorioCentral.getRepositorioIngresos().getIngresos().get(10).getDescripcion());  
	    
       
        for(  Ingreso ingreso   : repositorioCentral.getRepositorioIngresos().getIngresos()
			      
			      ) {
			    	
        				System.out.println(ingreso.getDescripcion()); 			    	
				}	
        
        for(  Egreso egreso   : repositorioCentral.getRepositorioEgresos().getEgresos()
			      
			      ) {
			    	
        					System.out.println(egreso.getDetalle()); 			    	
				}	
        
	
	}
    
	
}
