package main.java.dominio;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import main.java.criterios.CriterioEjecucion;
import main.java.factories.CriterioEjecucionFactory;
import spark.Request;
import spark.Response;

public class VinculadorOperaciones  {
	// preparo un builder para aceptar localdate
	private static final Gson gsonCentral = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
		 @Override
		 public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
			 return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
		 }
	}).create();
     

	public String vincular(Request request, Response response) {
		String requestBody = request.body();
		RepositorioCentral repoCentral = gsonCentral.fromJson(requestBody, RepositorioCentral.class);

		//el factory devuelve el criterio, con el string que viene en la request
		CriterioEjecucion criterioEjecucion = CriterioEjecucionFactory.getCriterioEjecucion(repoCentral.criterioEjecucion);
		return criterioEjecucion.ejecutar(repoCentral.repositorioIngresos, repoCentral.repositorioEgresos);
	}
}
