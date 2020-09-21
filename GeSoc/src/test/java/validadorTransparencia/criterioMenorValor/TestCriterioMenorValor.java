package validadorTransparencia.criterioMenorValor;

import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCriterioMenorValor {
    private OperacionEgreso operacionEgreso;
    AuxMenorValor critMenorValor = new AuxMenorValor();;
    String mensajeExito = "La Operacion de Egreso cumple con todas las politicas";

    @Before
    public void init(){
        this.operacionEgreso = new OperacionEgreso(null,500,null,null,null,null,null,null);
    }

    @Test (expected = Exception.class)
    public void TestPresupuestoElegidoNoEsMenorValor() throws Exception {
        Presupuesto unPresupuesto = new Presupuesto(null,500,null,null, this.operacionEgreso,true);
        Presupuesto otroPresupuesto = new Presupuesto(null,300,null,null, this.operacionEgreso,false);

        String mensaje = critMenorValor.validarMock(operacionEgreso);
    }

    @Test
    public void TestPresupuestoElegidoEsMenorValor() throws Exception {
        Presupuesto unPresupuesto = new Presupuesto(null,500,null,null, this.operacionEgreso,true);
        Presupuesto otroPresupuesto = new Presupuesto(null,1000,null,null, this.operacionEgreso,false);

        String mensaje = critMenorValor.validarMock(operacionEgreso);
        Assert.assertEquals(mensajeExito, mensaje);
    }

    @Test
    public void TestPresupuestoElegidoEsMenorValorEntreVarios() throws Exception {
        Presupuesto unPresupuesto = new Presupuesto(null,500,null,null, this.operacionEgreso,true);
        Presupuesto otroPresupuesto = new Presupuesto(null,1000,null,null, this.operacionEgreso,false);
        Presupuesto otroPresupuestoDeValorMinimo = new Presupuesto(null,500,null,null, this.operacionEgreso,false);

        String mensaje = critMenorValor.validarMock(operacionEgreso);
        Assert.assertEquals(mensajeExito, mensaje);
    }

}
