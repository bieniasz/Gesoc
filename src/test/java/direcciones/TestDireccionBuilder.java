package direcciones;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import direccion.Direccion;
import direccion.DireccionBuilder;
import direccion.ExcepcionesDireccion.FaltaLocacionException;
import direccion.ExcepcionesDireccion.LocacionNoValidaException;
import direccion.MLDireccionesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TestDireccionBuilder {

    private DireccionBuilder builder;

    @Before
    public void init(){
        this.builder = new DireccionBuilder();
        this.builder.setProveedorDatosDirecciones(new MLDireccionesService());
    }

    @Test
    public void DireccionContienePaisCorrecto() throws Exception {
        Direccion direccionConstruidaPorBuilder = this.builder
                .setPais("Argentina")
                .setProvincia("Corrientes")
                .setCiudad("Bella Vista")
                .setCalle("Maipu")
                .setAltura("768")
                .setMonedaLocal("ARS")
                .build();

       Assert.assertEquals(direccionConstruidaPorBuilder.pais.nombre, "Argentina");
       Assert.assertEquals(direccionConstruidaPorBuilder.provincia.nombre, "Corrientes");
       Assert.assertEquals(direccionConstruidaPorBuilder.ciudad.nombre, "Bella Vista");
       Assert.assertEquals(direccionConstruidaPorBuilder.calle, "Maipu");
       Assert.assertEquals(direccionConstruidaPorBuilder.altura, "768");
       Assert.assertEquals(direccionConstruidaPorBuilder.monedaLocal.nombre, "ARS");
    }

    @Test(expected = FaltaLocacionException.class)
    public void FaltaPaisDireccionNoSeCrea() throws Exception {
        this.builder
                .setProvincia("Corrientes")
                .setCiudad("Bella Vista")
                .setCalle("Maipu")
                .setAltura("768")
                .setMonedaLocal("ARS")
                .build();
    }

    @Test(expected = FaltaLocacionException.class)
    public void FaltaProvinciaDireccionNoSeCrea() throws Exception {
        this.builder
                .setPais("Argentina")
                .setCiudad("Bella Vista")
                .setCalle("Maipu")
                .setAltura("768")
                .setMonedaLocal("ARS")
                .build();
    }

    @Test(expected = FaltaLocacionException.class)
    public void FaltaCiudadDireccionNoSeCrea() throws Exception {
        this.builder
                .setPais("Argentina")
                .setProvincia("Corrientes")
                .setCalle("Maipu")
                .setAltura("768")
                .setMonedaLocal("ARS")
                .build();
    }

    @Test(expected = FaltaLocacionException.class)
    public void FaltaCalleDireccionNoSeCrea() throws Exception {
        this.builder
                .setPais("Argentina")
                .setProvincia("Corrientes")
                .setCiudad("Bella Vista")
                .setAltura("768")
                .setMonedaLocal("ARS")
                .build();
    }

    @Test(expected = FaltaLocacionException.class)
    public void FaltaAlturaDireccionNoSeCrea() throws Exception {
        this.builder
                .setPais("Argentina")
                .setProvincia("Corrientes")
                .setCiudad("Bella Vista")
                .setCalle("Maipu")
                .setMonedaLocal("ARS")
                .build();
    }

    @Test(expected = FaltaLocacionException.class)
    public void FaltaMonedaLocalDireccionNoSeCrea() throws Exception {
        this.builder
                .setPais("Argentina")
                .setProvincia("Corrientes")
                .setCiudad("Bella Vista")
                .setCalle("Maipu")
                .setAltura("768")
                .build();
    }

    @Test(expected = FaltaLocacionException.class)
    public void DireccionNoSeCreaSiDatoPaisNoEntraEnOrden() throws Exception {
        this.builder
                .setProvincia("Corrientes")
                .setPais("Argentina")
                .setCiudad("Bella Vista")
                .setCalle("Maipu")
                .setAltura("768")
                .setMonedaLocal("ARS")
                .build();
    }

    @Test(expected = FaltaLocacionException.class)
    public void DireccionNoSeCreaSiDatoProvinciaNoEntraEnOrden() throws Exception {
        this.builder
                .setPais("Argentina")
                .setCiudad("Bella Vista")
                .setProvincia("Corrientes")
                .setCalle("Maipu")
                .setAltura("768")
                .setMonedaLocal("ARS")
                .build();
    }

    @Test(expected = FaltaLocacionException.class)
    public void DireccionNoSeCreaSiDatoMonedaNoEntraEnOrden() throws Exception {
        this.builder
                .setMonedaLocal("ARS")
                .setPais("Argentina")
                .setProvincia("Corrientes")
                .setCiudad("Bella Vista")
                .setCalle("Maipu")
                .setAltura("768")
                .build();
    }

    @Test(expected = LocacionNoValidaException.class)
    public void ErrorSiElPaisEsInvalido() throws Exception {
        this.builder
                .setPais("Zzzzz");

    }

    @Test(expected = LocacionNoValidaException.class)
    public void ErrorSiLaProvinciaEsInvalida() throws Exception {
        this.builder
                .setPais("Argentina")
                .setProvincia("Zzzzzz");

    }

    @Test(expected = LocacionNoValidaException.class)
    public void ErrorSiLaCiudadEsInvalida() throws Exception {
        this.builder
                .setPais("Argentina")
                .setProvincia("Corrientes")
                .setCiudad("Zzzzzz");

    }


}
