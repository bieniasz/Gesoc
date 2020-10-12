package domain.entities.direccion;

import domain.entities.direccion.ExcepcionesDireccion.FaltaLocacionException;

import java.io.IOException;
import java.util.List;

public interface DireccionesService {

    public List<String> getCiudades(String nombreProvincia) throws IOException, FaltaLocacionException;
    public List<String> getProvinciasDisponibles(String nombrePais) throws IOException, FaltaLocacionException;
    public List<String> getPaisesDisponibles() throws IOException;
    public String getMonedaPais(String nombrePais) throws IOException;


}
