package tamanioEmpresa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import organizacion.categoria.Empresa;

public class TestEmpresaSectorServicios {
    private Empresa empresaTest;

    @Before
    public void init(){
        empresaTest = new Empresa(200, Actividad.SERVICIOS, 0);
    }

    @Test
    public void empresaServiciosEsMicro() {
        //topeMicro: $8.500.000
        //PromVentas = $4.000.512
        empresaTest.setPromedioVentasAnuales(4000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MICRO, tamano);
    }

    @Test
    public void empresaServiciosEsPequena() {
        //topePequeña: $50.950.000
        //PromVentas = $32.000.512
        empresaTest.setPromedioVentasAnuales(32000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.PEQUENIA, tamano);
    }

    @Test
    public void empresaServiciosEsMedianaT1() {
        //topeMedianaTramo1: $425.170.000
        //PromVentas = $128.000.512
        empresaTest.setPromedioVentasAnuales(128000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T1, tamano);
    }

    @Test
    public void empresaServiciosEsMedianaT2() {
        //topeMedianaTramo2: $607.210.000
        //PromVentas = $512.000.512
        empresaTest.setPromedioVentasAnuales(512000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T2, tamano);
    }

    @Test
    public void empresaServiciosNoAplica() {
        //topeMedianaTramo2: $607.210.000
        //PromVentas = $2048.000.512
        empresaTest.setPromedioVentasAnuales(2048000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.OVERFLOW, tamano);
    }
}
