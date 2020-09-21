package direccion;

import direccion.AtributosPersistentes.Ciudad;
import direccion.AtributosPersistentes.Moneda;
import direccion.AtributosPersistentes.Pais;
import direccion.AtributosPersistentes.Provincia;
import direccion.ExcepcionesDireccion.FaltaLocacionException;
import direccion.ExcepcionesDireccion.LocacionNoValidaException;

import java.io.IOException;
import java.util.List;



public class DireccionBuilder {
    private String calle;
    private String altura;
    private Ciudad ciudad;
    private Provincia provincia;
    private Pais pais;
    private Moneda monedaLocal;

    private DireccionesService proveedorDatosDirecciones;

    public void setProveedorDatosDirecciones(DireccionesService proveedorDatosDirecciones) {
        this.proveedorDatosDirecciones = proveedorDatosDirecciones;
    }

    public DireccionBuilder setPais(String paisElegioPorUsuario) throws IOException, LocacionNoValidaException {
        List<String> paisesDisponibles = proveedorDatosDirecciones.getPaisesDisponibles();

        if( !paisesDisponibles.contains(paisElegioPorUsuario) )
            throw new LocacionNoValidaException("Pais", paisElegioPorUsuario);

        this.pais = new Pais(paisElegioPorUsuario);

        return this;
    }

    public DireccionBuilder setMonedaLocal(String monedaLocal) throws IOException, LocacionNoValidaException, FaltaLocacionException {
        if ( this.pais == null )
            throw new FaltaLocacionException("Pais");

        String moneda = proveedorDatosDirecciones.getMonedaPais(this.pais.nombre);
        if( !moneda.contains(monedaLocal) )
            throw new LocacionNoValidaException("Moneda", monedaLocal);

        this.monedaLocal = new Moneda(monedaLocal);

        return this;
    }

    public DireccionBuilder setProvincia(String provincia) throws IOException, LocacionNoValidaException, FaltaLocacionException {
        if ( this.pais == null )
            throw new FaltaLocacionException("Pais");

        List<String> provinciasDisponibles = proveedorDatosDirecciones.getProvinciasDisponibles(this.pais.nombre);
        if( !provinciasDisponibles.contains(provincia) )
            throw new LocacionNoValidaException("Provincia", provincia);

        this.provincia = new Provincia(provincia);

        return this;
    }

    public DireccionBuilder setCiudad(String ciudad) throws Exception {
        if ( this.pais == null )
            throw new FaltaLocacionException("Pais");

        if ( this.provincia == null )
            throw new FaltaLocacionException("Provincia");

        List<String> ciudadesDisponibles = proveedorDatosDirecciones.getCiudades(this.provincia.nombre);
        if( !ciudadesDisponibles.contains(ciudad) )
            throw new LocacionNoValidaException("Ciudad", ciudad);

        this.ciudad = new Ciudad(ciudad);

        return this;
    }

    public DireccionBuilder setCalle(String calle){
        this.calle = calle;

        return this;
    }

    public DireccionBuilder setAltura(String altura){
        this.altura = altura;

        return this;
    }

    public Direccion build() throws FaltaLocacionException {
        if ( this.pais == null )
            throw new FaltaLocacionException("Pais");

        if ( this.monedaLocal == null )
            throw new FaltaLocacionException("Moneda");

        if ( this.provincia == null )
            throw new FaltaLocacionException("Provincia");

        if ( this.ciudad == null )
            throw new FaltaLocacionException("Ciudad");

        if ( this.altura == null )
            throw new FaltaLocacionException("Altura");

        if ( this.calle == null )
            throw new FaltaLocacionException("Calle");

        Direccion direccion = new Direccion();
        direccion.calle = this.calle;
        direccion.altura = this.altura;
        direccion.ciudad = this.ciudad;
        direccion.provincia = this.provincia;
        direccion.monedaLocal = this.monedaLocal;
        direccion.pais = this.pais;

        return direccion;
    }

}
