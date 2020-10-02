package validadorTransparencia.criterioMenorValor;

import operacionComercial.DetalleEgreso;
import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import validacionSeleccionProveedor.criteriosSeleccionProveedor.MenorValor;

public class TestCriterioMenorValor {
    private OperacionEgreso operacionEgreso;
    AuxMenorValor critMenorValor = new AuxMenorValor();;
    String mensajeExito = "La Operacion de Egreso cumple con todas las politicas";

    @Before
    public void init(){
        this.operacionEgreso = new OperacionEgreso();
        DetalleEgreso det = new DetalleEgreso();
        det.valorTotal = 500.0;
        this.operacionEgreso.registrarDetalle(det);
    }

    @Test(expected = Exception.class)
    public void TestPresupuestoElegidoNoEsMenorValor() throws Exception {
        Presupuesto unPresupuesto = new Presupuesto();
        DetalleEgreso det1 = new DetalleEgreso();
        det1.valorTotal = 500.0;
        unPresupuesto.registrarDetalle(det1);
        unPresupuesto.setEgreso(this.operacionEgreso);
        unPresupuesto.setElegido(true);

        Presupuesto otroPresupuesto = new Presupuesto();
        DetalleEgreso det2 = new DetalleEgreso();
        det2.valorTotal = 300.0;
        otroPresupuesto.registrarDetalle(det2);
        otroPresupuesto.setEgreso(this.operacionEgreso);
        otroPresupuesto.setElegido(false);

        String mensaje = critMenorValor.validarMock(operacionEgreso);
    }

    @Test
    public void TestPresupuestoElegidoEsMenorValor() throws Exception {
        Presupuesto unPresupuesto = new Presupuesto();
        DetalleEgreso det1 = new DetalleEgreso();
        det1.valorTotal = 500.0;
        unPresupuesto.registrarDetalle(det1);
        unPresupuesto.setEgreso(this.operacionEgreso);
        unPresupuesto.setElegido(true);

        Presupuesto otroPresupuesto = new Presupuesto();
        DetalleEgreso det2 = new DetalleEgreso();
        det2.valorTotal = 1000.0;
        otroPresupuesto.registrarDetalle(det2);
        otroPresupuesto.setEgreso(this.operacionEgreso);
        otroPresupuesto.setElegido(false);

        String mensaje = critMenorValor.validarMock(operacionEgreso);
        Assert.assertEquals(mensajeExito, mensaje);
    }

    @Test
    public void TestPresupuestoElegidoEsMenorValorEntreVarios() throws Exception {
        //En este test el presupuesto elegido está entre varios con el mismo monto mínimo

        Presupuesto presupuestoElegido = new Presupuesto();
        DetalleEgreso det1 = new DetalleEgreso();
        det1.valorTotal = 500.0;
        presupuestoElegido.registrarDetalle(det1);
        presupuestoElegido.setEgreso(this.operacionEgreso);
        presupuestoElegido.setElegido(true);

        Presupuesto otroPresupuesto = new Presupuesto();
        DetalleEgreso det2 = new DetalleEgreso();
        det2.valorTotal = 1000.0;
        otroPresupuesto.registrarDetalle(det2);
        otroPresupuesto.setEgreso(this.operacionEgreso);
        otroPresupuesto.setElegido(false);

        Presupuesto unTercerPresupuesto = new Presupuesto();
        DetalleEgreso det3 = new DetalleEgreso();
        det3.valorTotal = 500.0;
        unTercerPresupuesto.registrarDetalle(det3);
        unTercerPresupuesto.setEgreso(this.operacionEgreso);
        unTercerPresupuesto.setElegido(false);

        String mensaje = critMenorValor.validarMock(operacionEgreso);
        Assert.assertEquals(mensajeExito, mensaje);
    }


    class AuxMenorValor extends MenorValor {
        //Esta clase existe para darle un valor de retorno ficticio a validar
        //y poder testear los casos de exito unitariamente
        //sin involucrar la suscripcion de validacion

        public String validarMock(OperacionEgreso operacionEgreso) throws Exception {
            super.validar(operacionEgreso);
            return "La Operacion de Egreso cumple con todas las politicas";
        }
    }
}