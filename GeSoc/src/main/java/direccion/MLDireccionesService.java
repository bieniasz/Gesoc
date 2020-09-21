package direccion;

import direccion.ExcepcionesDireccion.FaltaLocacionException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MLDireccionesService implements DireccionesService{

    private JSONArray paisesArray;
    private JSONObject informacionPaisObject;

    public List<String> getCiudades(String nombreProvincia) throws IOException, FaltaLocacionException {
        if( this.paisesArray == null)
            throw new FaltaLocacionException("Pais");

        if( this.informacionPaisObject == null)
            throw new FaltaLocacionException("Provincia");

        String id = MLJSONDataExtractor.obtenerIdDeLaProvincia(this.informacionPaisObject, nombreProvincia);
        String informacionProvinciaJson = this.llamarAServicio("https://api.mercadolibre.com/classified_locations/states/" + id);
        JSONObject ciudadesObject = new JSONObject(informacionProvinciaJson);

        List<String> ciudades = MLJSONDataExtractor.obtenerListaDeCiudadesDesdeJSONObject(ciudadesObject);
        return ciudades;
    }


    public List<String> getProvinciasDisponibles(String nombrePais) throws IOException, FaltaLocacionException {

        if( this.paisesArray == null)
            throw new FaltaLocacionException("Pais");

        String id = MLJSONDataExtractor.obtenerIdDelPais(this.paisesArray, nombrePais);
        String informacionPaisJson = this.llamarAServicio("https://api.mercadolibre.com/classified_locations/countries/" + id);
        this.informacionPaisObject = new JSONObject(informacionPaisJson);

        List<String> provincias = MLJSONDataExtractor.obtenerListaDeProvinciasDesdeJSONObject(this.informacionPaisObject);
        return provincias;
    }


    public String getMonedaPais(String nombrePais) throws IOException {
        String moneda = "";
        for (int i=0; i<paisesArray.length(); i++) {

            JSONObject country = paisesArray.getJSONObject(i);
            String pais = country.getString("name");
            if (pais.contains(nombrePais)){
                moneda = country.getString("currency_id");
            }
        }
        return moneda;
    }

    public List<String> getPaisesDisponibles() throws IOException {
        String jsonPaises = this.llamarAServicio("https://api.mercadolibre.com/classified_locations/countries");
        this.paisesArray = new JSONArray(jsonPaises);

        List<String> paises = MLJSONDataExtractor.obtenerListaPaisesDeJSONArray(paisesArray);
        return paises;
    }

    private String llamarAServicio(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);

        ResponseHandler< String > responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        String responseBody = httpclient.execute(httpget, responseHandler);

        return responseBody;
    }

}
