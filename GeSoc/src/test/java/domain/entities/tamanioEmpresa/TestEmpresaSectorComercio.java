package domain.entities.tamanioEmpresa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.entities.organizacion.categoria.Empresa;

public class TestEmpresaSectorComercio {
    private Empresa empresaTest;

    @Before
    public void init(){
        Actividad comercio = new Actividad();
        comercio.setTopeMicro(29740000);
        comercio.setTopePequenia(178860000);
        comercio.setTopeMedianaT1(1502750000);
        comercio.setTopeMedianaT2(2146810000);

        comercio.setTopeCantPersonalMicro(7);
        comercio.setTopeCantPersonalPequenia(35);
        comercio.setTopeCantPersonalMedianaTramo1(135);
        comercio.setTopeCantPersonalMedianaTramo2(345);

        empresaTest = new Empresa(100, comercio, 100, false);
    }

    @Test
    public void empresaComercioEsMicro() {
        //topeMicro: $29.740.000
        //PromVentas = $4.000.512
        empresaTest.setPromedioVentasAnuales(4000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MICRO, tamano);
    }

    @Test
    public void empresaComercioEsPequena() {
        //topePequeña: $178.860.000
        //PromVentas = $32.000.512
        empresaTest.setPromedioVentasAnuales(32000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.PEQUENIA, tamano);
    }

    @Test
    public void empresaComercioEsMedianaT1() {
        //topeMedianaTramo1: $1502.750.000
        //PromVentas = $512.000.512
        empresaTest.setPromedioVentasAnuales(512000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T1, tamano);
    }

    @Test
    public void empresaComercioEsMedianaT2() {
        //topeMedianaTramo2: $2146.810.000
        //PromVentas = $2048.000.512
        empresaTest.setPromedioVentasAnuales(2048000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T2, tamano);
    }

    @Test
    public void empresaComercioNoAplica() {
        //topeMedianaTramo2: $2146.810.000
        //PromVentas = $2147.000.512
        empresaTest.setPromedioVentasAnuales(2147000512);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        //Assert.assertEquals(Tamanios.OVERFLOW, tamano);
    }

    //Tests de empresas comisionistas -> Se evalua la cantidad de personal

    @Test
    public void empresaComercioComisionistaEsMicro() {
        //topeMicro: 7
        empresaTest.setCantidadDePersonal(7);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MICRO, tamano);
    }

    @Test
    public void empresaComercioComisionistaEsPequena() {
        //topePequenia: 35
        empresaTest.setCantidadDePersonal(32);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.PEQUENIA, tamano);
    }

    @Test
    public void empresaComercioComisionistaEsMedianaT1() {
        //topeMedianaT1: 135
        empresaTest.setCantidadDePersonal(128);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T1, tamano);
    }

    @Test
    public void empresaComercioComisionistaEsMedianaT2() {
        //topeMedianaT2: 345
        empresaTest.setCantidadDePersonal(256);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T2, tamano);
    }

    @Test
    public void empresaComercioComisionistaNoAplica() {
        //topeMedianaT2: 345
        empresaTest.setCantidadDePersonal(512);
        empresaTest.setComisionista(true);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.OVERFLOW, tamano);
    }
}
