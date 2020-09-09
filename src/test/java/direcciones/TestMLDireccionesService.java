package direcciones;

import direccion.ExcepcionesDireccion.FaltaLocacionException;
import direccion.MLDireccionesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestMLDireccionesService {

    private MLDireccionesService MLDireccionesService;

    @Before
    public void init() {
        this.MLDireccionesService = new MLDireccionesService();

    }

    @Test
    public void elServicioDevuelveLosPaisesDisponibles() throws IOException {
        List<String> paisesDisponibles = this.MLDireccionesService.getPaisesDisponibles();

        Assert.assertTrue(paisesDisponibles.size() != 0);
        Assert.assertTrue(paisesDisponibles.contains("Argentina"));
        Assert.assertTrue(paisesDisponibles.contains("Brasil"));
        Assert.assertTrue(paisesDisponibles.contains("Uruguay"));
    }

    @Test
    public void lasMonedasSonObtenidasCorrectamente() throws IOException {
        this.MLDireccionesService.getPaisesDisponibles(); //porque el metodo getMoneda usa el atributo que se guarda en este paso en su clase.

        String monedaArgentina = this.MLDireccionesService.getMonedaPais("Argentina");
        String monedaBrasil = this.MLDireccionesService.getMonedaPais("Brasil");
        String monedaChile = this.MLDireccionesService.getMonedaPais("Chile");
        String monedaReinoUnido = this.MLDireccionesService.getMonedaPais("Reino Unido");
        String monedaUruguay = this.MLDireccionesService.getMonedaPais("Uruguay");

        Assert.assertEquals("ARS",monedaArgentina);
        Assert.assertEquals("BRL",monedaBrasil);
        Assert.assertEquals("CLP",monedaChile);
        Assert.assertEquals("GBP",monedaReinoUnido);
        Assert.assertEquals("UYU",monedaUruguay);

    }

    @Test(expected = Exception.class)
    public void obtenerProvinciasArrojaExcepcion() throws IOException, FaltaLocacionException {
        this.MLDireccionesService.getPaisesDisponibles();
        this.MLDireccionesService.getProvinciasDisponibles("AR");

    }

    @Test
    public void obtenerProvinciasDeArgentinaDevuelveLalista() throws IOException, FaltaLocacionException {
        this.MLDireccionesService.getPaisesDisponibles();
        List<String> provinciasArgentinas =this.MLDireccionesService.getProvinciasDisponibles("Argentina");

        Assert.assertTrue(provinciasArgentinas.size() != 0);
        Assert.assertTrue(provinciasArgentinas.contains("Capital Federal"));

    }

    @Test
    public void obtenerProvinciasDeBrasilDevuelveLalista() throws IOException, FaltaLocacionException {
        this.MLDireccionesService.getPaisesDisponibles();
        List<String> provinciasBrasil = this.MLDireccionesService.getProvinciasDisponibles("Brasil");

        Assert.assertTrue(provinciasBrasil.size() != 0);
        Assert.assertTrue(provinciasBrasil.contains("Rio de Janeiro"));
    }

    @Test(expected = Exception.class)
    public void obtenerCiudadesArrojaExcepcion() throws IOException, FaltaLocacionException {
        List<String> ciudades = this.MLDireccionesService.getCiudades("Capital Federal");

    }

    @Test
    public void obtenerCiudadesDevuelveLaListaCorrectamente() throws IOException, FaltaLocacionException {
        this.MLDireccionesService.getPaisesDisponibles();
        this.MLDireccionesService.getProvinciasDisponibles("Argentina");
        List<String> ciudadesCABA = this.MLDireccionesService.getCiudades("Capital Federal");
        List<String> ciudadesGBANorte = this.MLDireccionesService.getCiudades("Bs.As. G.B.A. Norte");

        Assert.assertEquals(1, ciudadesCABA.size());
        Assert.assertEquals(true, ciudadesGBANorte.size() != 0);
        Assert.assertTrue(ciudadesGBANorte.contains("Escobar"));

    }

}

