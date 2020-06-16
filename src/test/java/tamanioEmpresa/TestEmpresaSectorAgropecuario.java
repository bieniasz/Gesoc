package tamanioEmpresa;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import organizacion.categoria.Empresa;

public class TestEmpresaSectorAgropecuario {
    private Empresa empresaTest;

    @Before
    public void init(){
        empresaTest = new Empresa(200, Actividad.AGROPECUARIO, 0);
    }

    @Test
    public void empresaAgroEsMicro() {
        //topeMicro: $12.890.000
        //PromVentas = $1.000.000
        empresaTest.setPromedioVentasAnuales(1000000);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MICRO, tamano);
    }

    @Test
    public void empresaAgroEsPequena() {
        //topePequeña: $48.480.000
        //PromVentas = $32.000.000
        empresaTest.setPromedioVentasAnuales(32000000);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.PEQUENIA, tamano);
    }

    @Test
    public void empresaAgroEsMedianaT1() {
        //topeMedianaTramo1: $345.430.000
        //PromVentas = $150.250.000
        empresaTest.setPromedioVentasAnuales(150250000);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T1, tamano);
    }

    @Test
    public void empresaAgroEsMedianaT2() {
        //topeMedianaTramo2: $547.890.000
        //PromVentas = $400.500.000
        empresaTest.setPromedioVentasAnuales(400500000);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.MEDIANA_T2, tamano);
    }

    @Test
    public void empresaAgroNoAplica() {
        //topeMedianaTramo2: $547.890.000
        //PromVentas = $600.500.300
        empresaTest.setPromedioVentasAnuales(600500300);
        String tamano = CalculadoraDeTamanio.calcularTamanio(empresaTest);

        Assert.assertEquals(Tamanios.OVERFLOW, tamano);
    }

}
