package tamanioEmpresa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import organizacion.categoria.Empresa;

public class TestEmpresaSectorIndustria {
    private Empresa empresaTest;

    @Before
    public void init(){
        empresaTest = new Empresa(100, Actividad.INDUSTRIA_Y_MINERIA, 100, false);
    }

    @Test
    public void empresaIndustriaEsMicro() {
        //topeMicro: $26.540.000
        //PromVentas = $16.000.512
        empresaTest.setPromedioVentasAnuales(16000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MICRO, tamano);
    }

    @Test
    public void empresaIndustriaEsPequena() {
        //topePequeña: $190.410.000
        //PromVentas = $64.000.512
        empresaTest.setPromedioVentasAnuales(64000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.PEQUENIA, tamano);
    }

    @Test
    public void empresaIndustriaEsMedianaT1() {
        //topeMedianaTramo1: $1190.330.000
        //PromVentas = $256.000.512
        empresaTest.setPromedioVentasAnuales(256000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T1, tamano);
    }

    @Test
    public void empresaIndustriaEsMedianaT2() {
        //topeMedianaTramo2: $1739.590.000
        //PromVentas = $1520.000.512
        empresaTest.setPromedioVentasAnuales(1520000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T2, tamano);
    }

    @Test
    public void empresaIndustriaNoAplica() {
        //topeMedianaTramo2: $1739.590.000
        //PromVentas = $2048.000.512
        empresaTest.setPromedioVentasAnuales(2048000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.OVERFLOW, tamano);
    }

    //Tests de empresas comisionistas -> Se evalua la cantidad de personal

    @Test
    public void empresaIndustriaComisionistaEsMicro() {
        //topeMicro: 15
        empresaTest.setCantidadDePersonal(8);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MICRO, tamano);
    }

    @Test
    public void empresaIndustriaComisionistaEsPequena() {
        //topePequenia: 60
        empresaTest.setCantidadDePersonal(32);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.PEQUENIA, tamano);
    }

    @Test
    public void empresaIndustriaComisionistaEsMedianaT1() {
        //topeMedianaT1: 235
        empresaTest.setCantidadDePersonal(128);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T1, tamano);
    }

    @Test
    public void empresaIndustriaComisionistaEsMedianaT2() {
        //topeMedianaT2: 655
        empresaTest.setCantidadDePersonal(256);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T2, tamano);
    }

    @Test
    public void empresaIndustriaComisionistaNoAplica() {
        //topeMedianaT2: 655
        empresaTest.setCantidadDePersonal(1024);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.OVERFLOW, tamano);
    }
}
