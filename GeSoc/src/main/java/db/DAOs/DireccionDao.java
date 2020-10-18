package db.DAOs;

import domain.entities.direccion.AtributosPersistentes.Ciudad;
import domain.entities.direccion.AtributosPersistentes.Moneda;
import domain.entities.direccion.AtributosPersistentes.Pais;
import domain.entities.direccion.AtributosPersistentes.Provincia;
import domain.entities.direccion.Direccion;

public interface DireccionDao {

    public void guardarDireccion(Direccion direccion);
    public void guardarCiudad(Ciudad ciudad);
    public void guardarProvincia(Provincia provincia);
    public void guardarPais(Pais pais);
    public void guardarMoneda(Moneda moneda);
}
