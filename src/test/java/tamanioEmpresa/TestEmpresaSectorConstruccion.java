package tamanioEmpresa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import organizacion.categoria.Empresa;

public class TestEmpresaSectorConstruccion {
    private Empresa empresaTest;

    @Before
    public void init(){
        empresaTest = new Empresa(100, Actividad.CONSTRUCCION, 100, false);
    }

    @Test
    public void empresaConstruccionEsMicro() {
        //topeMicro: $15.230.000
        //PromVentas = $4.000.512
        empresaTest.setPromedioVentasAnuales(4000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MICRO, tamano);
    }

    @Test
    public void empresaConstruccionEsPequena() {
        //topePequeña: $90.310.000
        //PromVentas = $32.000.512
        empresaTest.setPromedioVentasAnuales(32000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.PEQUENIA, tamano);
    }

    @Test
    public void empresaConstruccionEsMedianaT1() {
        //topeMedianaTramo1: $503.880.000
        //PromVentas = $128.000.512
        empresaTest.setPromedioVentasAnuales(128000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T1, tamano);
    }

    @Test
    public void empresaConstruccionEsMedianaT2() {
        //topeMedianaTramo2: $755.740.000
        //PromVentas = $512.000.512
        empresaTest.setPromedioVentasAnuales(512000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T2, tamano);
    }

    @Test
    public void empresaConstruccionNoAplica() {
        //topeMedianaTramo2: $755.740.000
        //PromVentas = $2048.000.512
        empresaTest.setPromedioVentasAnuales(2048000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.OVERFLOW, tamano);
    }

    //Tests de empresas comisionistas -> Se evalua la cantidad de personal

    @Test
    public void empresaConstruccionComisionistaEsMicro() {
        //topeMicro: 12
        empresaTest.setCantidadDePersonal(7);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MICRO, tamano);
    }

    @Test
    public void empresaConstruccionComisionistaEsPequena() {
        //topePequenia: 45
        empresaTest.setCantidadDePersonal(32);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.PEQUENIA, tamano);
    }

    @Test
    public void empresaConstruccionComisionistaEsMedianaT1() {
        //topeMedianaT1: 200
        empresaTest.setCantidadDePersonal(128);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T1, tamano);
    }

    @Test
    public void empresaConstruccionComisionistaEsMedianaT2() {
        //topeMedianaT2: 590
        empresaTest.setCantidadDePersonal(256);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T2, tamano);
    }

    @Test
    public void empresaConstruccionComisionistaNoAplica() {
        //topeMedianaT2: 590
        empresaTest.setCantidadDePersonal(1024);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.OVERFLOW, tamano);
    }
}
