package direccion;

import direccion.ExcepcionesDireccion.FaltaLocacionException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public interface DireccionesService {

    public List<String> getCiudades(String nombreProvincia) throws IOException, FaltaLocacionException;
    public List<String> getProvinciasDisponibles(String nombrePais) throws IOException, FaltaLocacionException;
    public List<String> getPaisesDisponibles() throws IOException;
    public String getMonedaPais(String nombrePais) throws IOException;


}
