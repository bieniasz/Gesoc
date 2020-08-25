package direccion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MercadoLibreJSONHandler {


    static List<String> obtenerListaPaisesDeJSONArray(JSONArray paisesArray) {
        List<String> paises =  new ArrayList<String>();
        for (int i=0; i<paisesArray.length(); i++) {
            JSONObject country = paisesArray.getJSONObject(i);
            paises.add(country.getString("name"));
        }

        return paises;
    }

    static List<String> obtenerListaDeProvinciasDesdeJSONObject(JSONObject informacionPaisObject) {
        JSONArray provinciasDisponibles = informacionPaisObject.getJSONArray("states");
        List<String> provincias = new ArrayList<String>();
        for (int i=0; i<provinciasDisponibles.length(); i++) {

            JSONObject provincia = provinciasDisponibles.getJSONObject(i);
            String nombreProvincia = provincia.getString("name");
            provincias.add(nombreProvincia);
        }
        return provincias;
    }

    static String obtenerIdDelPais(JSONArray paisesArray, String nombrePais) {
        String id = "";
        for (int i=0; i<paisesArray.length(); i++) {

            JSONObject country = paisesArray.getJSONObject(i);
            String pais = country.getString("name");
            if (pais.contains(nombrePais)){
                id = country.getString("id");
            }
        }
        return id;
    }

    static List<String> obtenerListaDeCiudadesDesdeJSONObject(JSONObject ciudadesObject) {
        JSONArray ciudadesDisponibles = ciudadesObject.getJSONArray("cities");
        List<String> ciudades = new ArrayList<String>();
        for (int i=0; i<ciudadesDisponibles.length(); i++) {

            JSONObject ciudad = ciudadesDisponibles.getJSONObject(i);
            String nombreCiudad = ciudad.getString("name");
            ciudades.add(nombreCiudad);
        }
        return ciudades;
    }

    static String obtenerIdDeLaProvincia(JSONObject informacionPaisObject, String nombreProvincia) {
        String id = "";
        JSONArray provincias = informacionPaisObject.getJSONArray("states");
        for (int i=0; i<provincias.length(); i++) {

            JSONObject provincia = provincias.getJSONObject(i);
            String provinciaString = provincia.getString("name");
            if (provinciaString.contains(nombreProvincia)){
                id = provincia.getString("id");
            }
        }
        return id;
    }
}
